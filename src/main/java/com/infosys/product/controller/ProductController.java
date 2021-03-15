package com.infosys.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.product.dto.BuyerDTO;
import com.infosys.product.dto.ProductDTO;
import com.infosys.product.dto.SubscribedProductDTO;
import com.infosys.product.sevice.ProductService;

@RestController
public class ProductController {
Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	@Autowired
	Environment environment;

	
	@GetMapping(value = "/api/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		logger.info("Fetching all products");
		System.out.println("In controller");
		try {
		List<ProductDTO> r=productService.getAllProducts();
		ResponseEntity<List<ProductDTO>> response=new ResponseEntity<List<ProductDTO>>(r,HttpStatus.OK);
		return response;
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
	}
	
	
	@GetMapping(value = "/api/{category}/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
		logger.info("Product details for category {}", category);
		try {
		List<ProductDTO> r=productService.getAllProductsByCategory(category);
		ResponseEntity<List<ProductDTO>> response=new ResponseEntity<List<ProductDTO>>(r,HttpStatus.OK);
		return response;
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
		ResponseEntity<List<ProductDTO>> response=new ResponseEntity<List<ProductDTO>>(r,HttpStatus.OK);
		return response;
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
	}
	@PostMapping(value="/api/subscriptions/add/",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSubscription(@RequestBody SubscribedProductDTO subscribedProductDTO){
		try {
			BuyerDTO buyerDTO=new RestTemplate().getForObject("http://localhost:8300/api/buyer/"+subscribedProductDTO.getBuyerId(),BuyerDTO.class);
			productService.addSubscrption(subscribedProductDTO,buyerDTO);
			String success="subscription added successfully";
			ResponseEntity<String> response=new ResponseEntity<String>(success,HttpStatus.OK);
			return response;
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
		
	}
	@GetMapping(value="/api/subscriptions/{subid}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<SubscribedProductDTO> getSpecificSubscription(@PathVariable Integer subid){
		try{
			SubscribedProductDTO s=productService.getSpeceficSubscription(subid);
			ResponseEntity<SubscribedProductDTO> response=new ResponseEntity<SubscribedProductDTO>(s,HttpStatus.OK);
			return response;
			
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		  }
		
	}
	
	
	@DeleteMapping(value="api/products/removeProducts/{sellerId}" )
	public ResponseEntity<String> removeStock(@PathVariable Integer sellerId){
		try {
		if(productService.removeStock(sellerId)) {
			String success="Product stock removed successfully";
			ResponseEntity<String> response=new ResponseEntity<String>(success,HttpStatus.OK);
			return response;
		}
		else
			throw new Exception("No product found with the seller");
		}
		catch(Exception e)
		  {
			  throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
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
	@DeleteMapping(value="/api/product/delete/{prodId}")
    public String removeProducts(@PathVariable Integer prodId){
    	try {
    		productService.deleteProduct(prodId);
    		String success="Product successfully deleted";
    		return success;
    	}
    	catch(Exception e) {
    		return environment.getProperty(e.getMessage());
    	}
    	
	
      }
	
}

	


