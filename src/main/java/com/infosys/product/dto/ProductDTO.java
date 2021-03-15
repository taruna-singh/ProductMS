package com.infosys.product.dto;




import com.infosys.product.entity.Product;

public class ProductDTO {
	Integer prodId;
	
	String brand;
	String category;
	 String description;
	String image;
	double price;
	String productName;
	Integer rating;
	 Integer sellerId;
	 Integer stock;
	 String subcategory;
	 public Integer getProdId() {
			return prodId;
		}
		public void setProdId(Integer prodId) {
			this.prodId = prodId;
		}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public static ProductDTO valueOf(Product product) {
		ProductDTO productdto=new ProductDTO();
		productdto.setProdId(product.getProdId());
		productdto.setBrand(product.getBrand());
		productdto.setCategory(product.getCategory());
		productdto.setDescription(product.getDescription());
		productdto.setImage(product.getImage());
		productdto.setPrice(product.getPrice());
		productdto.setProductName(product.getProductName());
		productdto.setRating(product.getRating());
		productdto.setSellerId(product.getSellerId());
		productdto.setStock(product.getStock());
		productdto.setSubcategory(product.getSubcategory());
		return productdto;
		
	}
	public Product createEntity() {
		Product product=new Product();
		product.setBrand(this.getBrand());
		product.setCategory(this.getCategory());
		product.setDescription(this.getDescription());
		product.setImage(this.getImage());
		product.setPrice(this.getPrice());
		product.setProdId(this.getProdId());
		product.setProductName(this.getProductName());
		product.setRating(this.getRating());
		product.setSellerId(this.getSellerId());
		product.setStock(this.getStock());
		product.setSubcategory(this.getSubcategory());
		return product;
	}
	
		
	}

