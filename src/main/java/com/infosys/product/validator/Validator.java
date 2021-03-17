package com.infosys.product.validator;

import com.infosys.product.dto.ProductDTO;

public class Validator {
	public static boolean validate(ProductDTO productDTO)throws Exception{
		if(validateStock(productDTO.getStock())!=true)
			throw new Exception("VALIDATOR.INVALID_STOCK_VALUE");
		if(validateName(productDTO.getProductName())!=true)
			throw new Exception("VALIDATOR.INVALID_NAME");
		if(validateDescription(productDTO.getDescription())!=true)
			throw new Exception("VALIDATOR.INVALID_DESCRIPTION");
		if(validatePrice(productDTO.getPrice())!=true)
			throw new Exception("VALIDATOR.INVALID_PRICE");
		if(validateImage(productDTO.getImage())!=true)
			throw new Exception("Validator.INVALID_IMAGE_FORMAT");
		else
			return true;
		
	}
	public static boolean validateName(String productName) {
		String regex="^[A-Za-z]+([\\s]*[A-Za-z]+)*$";                 

        if(productName!=null&&productName.length()<=100&&productName.matches(regex))
            return true;
          else
            return false;
     }
	public static boolean validateDescription(String description) {
		if(description!=null&&description.length()>0&&description.length()<=500) {
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
	if(image!=null&&(image.endsWith("png\"")||image.endsWith("jpeg\"")))
		return true;
	else
		return false;
}
}

