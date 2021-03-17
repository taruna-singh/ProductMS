package com.infosys.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.product.dto.BuyerDTO;
import com.infosys.product.dto.ProductDTO;
import com.infosys.product.dto.ProductsOrderedDTO;
import com.infosys.product.dto.SubscribedProductDTO;
import com.infosys.product.sevice.ProductService;

@RestController
@CrossOrigin
public class ProductController {
Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	@Autowired
	Environment environment;

	@Value("${user.uri}")
	String userUri;
	
	@GetMapping(value = "/api/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		logger.info("Fetching all products");
		System.out.println("In controller");
		try {
		List<ProductDTO> r=productService.getAllProducts();
		return new ResponseEntity<List<ProductDTO>>(r,HttpStatus.OK);
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
	}
	
	@GetMapping(value="/api/products/Id/{prodId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getProductById(@PathVariable Integer prodId)
	{
		try
		{
			return productService.getProductByProductId(prodId);
		}catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage(),e);
		}
	}
	
	@GetMapping(value = "/api/products/category/{category}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
		logger.info("Product details for category {}", category);
		try {
		List<ProductDTO> r=productService.getAllProductsByCategory(category);
		return new ResponseEntity<List<ProductDTO>>(r,HttpStatus.OK);
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
	}
	
	
	@GetMapping(value = "/api/products/{productName}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String productName) {
		logger.info("Product details for product name {}", productName);
		try{
			List<ProductDTO> r=productService.getAllProductsByName(productName);
		return new ResponseEntity<List<ProductDTO>>(r,HttpStatus.OK);
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
	}
	@PostMapping(value="/api/subscriptions/add/",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSubscription(@RequestBody SubscribedProductDTO subscribedProductDTO){
		try {
			BuyerDTO buyerDTO=new RestTemplate().getForObject(userUri+"buyer/"+subscribedProductDTO.getBuyerId(),BuyerDTO.class);
			productService.addSubscrption(subscribedProductDTO,buyerDTO);
			return new ResponseEntity<String>("subscription added successfully",HttpStatus.OK);
		}
		catch(Exception e)
		  {
			  return new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		  }
		
	}
	@GetMapping(value="/api/subscriptions/{subid}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SubscribedProductDTO> getSpecificSubscription(@PathVariable Integer subid){
		try{
			SubscribedProductDTO s=productService.getSpecificSubscription(subid);
		     return new ResponseEntity<SubscribedProductDTO>(s,HttpStatus.OK);
			
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
		
	}
	
	
	@DeleteMapping(value="api/products/removeProducts/{sellerId}" )
	public ResponseEntity<String> deleteSellerProducts(@PathVariable Integer sellerId){
		try {
		if(productService.deleteSellerProducts(sellerId)) {
			String success="Product stock removed successfully";
			return new ResponseEntity<String>(success,HttpStatus.OK);
		}
		else
			throw new Exception("No product found with the seller");
		}
		catch(Exception e)
		  {
			  return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
		  }
	}
	@PostMapping(value="/api/products/add/",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProducts(@RequestBody ProductDTO productDTO){
		try {
			productService.addProduct(productDTO);
			String success="product successfully added";
			ResponseEntity<String> response=new ResponseEntity<String>(success,HttpStatus.OK);
			return response;
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
			
		}
	@DeleteMapping(value="/api/products/delete/{prodId}")
    public String deleteProduct(@PathVariable Integer prodId){
    	try {
    		productService.deleteProduct(prodId);
    		String success="Product successfully deleted";
    		return success;
    	}
    	catch(Exception e) {
    		return environment.getProperty(e.getMessage());
    	}
    	
	
      }
	@PostMapping(value="/api/products/reduceStock",consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean reduceStock(@RequestBody List<ProductsOrderedDTO> productsOrdered)
	{  
		productService.reduceStock(productsOrdered);
		return true;
	}
	
	@PutMapping(value="/api/products/updateStock",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStock(@RequestBody ProductDTO productDTO)
	{
		try {
			if(productService.updateStock(productDTO))
					{
				return new ResponseEntity<String>("Successfully updated Stock!!",HttpStatus.OK);
					}
			else
				throw new Exception("Unable to update stock!!");
		}catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}

	


