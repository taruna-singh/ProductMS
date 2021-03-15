package com.infosys.product.sevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.product.dto.BuyerDTO;
import com.infosys.product.dto.ProductDTO;
import com.infosys.product.dto.SubscribedProductDTO;
import com.infosys.product.entity.Product;
import com.infosys.product.entity.SubscribedProduct;
import com.infosys.product.repository.ProductRepository;
import com.infosys.product.repository.SubscribedProductRepository;
import com.infosys.product.validator.Validator;

@Service

public class ProductService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;
 @Autowired
 private SubscribedProductRepository subrepo;
 
    
    public List<ProductDTO> getAllProducts()throws Exception {
        List<Product> products= productRepository.findAll();
List<ProductDTO> productDTOs = new ArrayList<>();
if(!products.isEmpty()) {
		
		for(Product pro:products) {
			ProductDTO productDTO = ProductDTO.valueOf(pro);
			productDTOs.add(productDTO);
		}
		logger.info("Product details: ", productDTOs);
		return productDTOs;
	}
else {
	throw new Exception("SERVICE.invalid_request");
}
    }
    

    
    public List<ProductDTO> getAllProductsByCategory(String category)throws Exception {
        List<Product> products=productRepository.findAllBycategory(category);
List<ProductDTO> productDTOs = new ArrayList<>();
if(!products.isEmpty()) {
		for(Product pro:products) {
			ProductDTO productDTO = ProductDTO.valueOf(pro);
			productDTOs.add(productDTO);
		}
		logger.info("Product details according to category : ", productDTOs);
		return productDTOs;
	}
else {
	throw new Exception("SERVICE.invalid_category");
}
    } 
   public void addSubscrption(SubscribedProductDTO subscribedProductDTO,BuyerDTO buyerDTO)throws Exception {
	   
	if(buyerDTO.getIsPrivileged()==1) {
	SubscribedProduct sp=subscribedProductDTO.createEntity();
	subrepo.save(sp);
	}
	else
	{
		throw new Exception("SERVICE.FAILED_SUBSCRIPTION");
	}
 }
   public SubscribedProductDTO getSpeceficSubscription(Integer subid)throws Exception {
	   Optional<SubscribedProduct> optSub=subrepo.findById(subid);
	   SubscribedProductDTO subscribedproductDTO=null;
	   if(optSub.isPresent()) {
		   SubscribedProduct subscribedProduct=optSub.get();
	   
		   subscribedproductDTO=SubscribedProductDTO.valueOf(subscribedProduct);
	   
	   return subscribedproductDTO;
	   }
	   else
		{
			throw new Exception("SERVICE.SUBSCRIPTION_NOT_FOUND");
		}
   }
   
   
   
   
    public List<ProductDTO> getAllProductsByName(String name)throws Exception {
        List<Product> products=productRepository.findAllByproductName(name);
List<ProductDTO> productDTOs = new ArrayList<>();
	if(!products.isEmpty()) {	
		for(Product pro:products) {
			ProductDTO productDTO = ProductDTO.valueOf(pro);
			productDTOs.add(productDTO);
		}
		logger.info("Product details according to name : ", productDTOs);
		return productDTOs;
	}
	
    else {
    	throw new Exception("SERVICE.invalid_product_name");
    }
    
    }
    public boolean removeStock(Integer sellerId) {
    	List<Product> products=productRepository.findAllBysellerId(sellerId);
    	if(!products.isEmpty()) {
    	for(Product pro:products) {
    		pro.setStock(0);
    		productRepository.save(pro);
    	
			
		}
    	return true;
  
    }
    	else {
    	 return false;
    	}
}
    public void addProduct(ProductDTO productDTO)throws Exception {
    	Validator.validate(productDTO);
    	Product product=productDTO.createEntity();
    	productRepository.save(product);
    }
    public void deleteProduct(Integer prodId)throws Exception {
    	Optional<Product> product=productRepository.findById(prodId);
    	if(product.isPresent()) {
    	Validator.validateStock(product.get().getStock());
    	productRepository.deleteById(prodId);
    }
    	else {
    		throw new Exception("SERVICE.NOT_FOUND");
    	}
    	
}
}
