package com.infosys.product.dto;


import com.infosys.product.entity.SubscribedProduct;

public class SubscribedProductDTO {

	Integer subId;
	
	Integer buyerId;
	 
	 Integer prodId;
	 
	Integer quantity;
	public Integer getSubId() {
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
	public static SubscribedProductDTO valueOf(SubscribedProduct subscribedproduct) {
		SubscribedProductDTO subscribeddto=new SubscribedProductDTO();
		subscribeddto.setBuyerId(subscribedproduct.getBuyerId());
		subscribeddto.setProdId(subscribedproduct.getProdId());
		subscribeddto.setQuantity(subscribedproduct.getQuantity());
		return subscribeddto;
	}
	public SubscribedProduct createEntity() {
		SubscribedProduct sp=new SubscribedProduct();
		sp.setBuyerId(this.getBuyerId());
		sp.setProdId(this.getProdId());
		sp.setQuantity(this.getQuantity());
		sp.setSubId(this.getSubId());
		return sp;
		
	}

}
