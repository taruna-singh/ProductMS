package com.infosys.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	@Id
	@Column(name="prodid",nullable=false,length=11)
	private Integer prodId;
	@Column(nullable=false,length=255)
	private String brand;
	@Column(nullable=false,length=255)
	private String category;
	@Column(nullable=false,length=255)
	private String description;
	@Column(nullable=false,length=255)
	private String image;
	@Column(nullable=false,precision=10,scale=2)
	private double price;
	@Column(name="productname",nullable=false,length=255)
	private String productName;
	private Integer rating;
	@Column(name="sellerid",nullable=false,length=255)
	private Integer sellerId;
	@Column(nullable=false,length=255)
	private Integer stock;
	private String subcategory;
	public Integer getProdId() {
		return prodId;
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
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	
}