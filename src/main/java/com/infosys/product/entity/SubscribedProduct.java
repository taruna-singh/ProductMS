package com.infosys.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subscribedproduct")
public class SubscribedProduct {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="subid",nullable=false,length=11)
 private Integer subId;
 @Column(name="buyerid",nullable=false,length=11)
 private Integer buyerId;
 @Column(name="prodid",nullable=false,length=11)
 private Integer prodId;
 @Column(nullable=false,length=11)
 private Integer quantity;
 public Integer getSubid() {
	return subId;
}
public void setSubId(Integer subId) {
	this.subId = subId;
}
public Integer getBuyerId() {
	return buyerId;
}
public void setBuyerId(Integer buyerId) {
	this.buyerId = buyerId;
}
public Integer getProdId() {
	return prodId;
}
public void setProdId(Integer prodId) {
	this.prodId = prodId;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
}
