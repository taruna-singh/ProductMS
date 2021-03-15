package com.infosys.product.validator;

import com.infosys.product.dto.ProductDTO;

public class Validator {
	public static boolean validate(ProductDTO productDTO)throws Exception{
		if(!validateName(productDTO.getProductName()))
			throw new Exception("VALIDATOR.INVALID_NAME");
		if(!validateDescription(productDTO.getDescription()))
			throw new Exception("VALIDATOR.INVALID_DESCRIPTION");
		if(!validatePrice(productDTO.getPrice()))
			throw new Exception("VALIDATOR.INVALID_PRICE");
		if(!validateStock(productDTO.getStock()))
			throw new Exception("VALIDATOR.INVALID_STOCK_VALUE");
		if(!validateImage(productDTO.getImage()))
			throw new Exception("Validator.INVALID_IMAGE_FORMAT");
		else
			return true;
		
	}
	public static boolean validateName(String productName) {
		String regex="^[A-Za-z]+([\\s]*[A-Za-z]+)*$)";                 

        if(productName.matches(regex)&&productName.length()<=100)
            return true;
          else
            return false;
     }
	public static boolean validateDescription(String description) {
		if(description.length()>0&&description.length()<=500) {
			return true;
		}
		else
			return false;
		}
	
   public static boolean validatePrice(double price) {
	   if(price>=200) {
		   return true;
	   }
	   else
		   return false;
   }

public static boolean validateStock(Integer stock) {
	if(stock>=10)
		return true;
	else
		return false;
}
public static boolean validateImage(String image) {
	if(image.endsWith("png")||image.endsWith("jpeg"))
		return true;
	else
		return false;
}
}

