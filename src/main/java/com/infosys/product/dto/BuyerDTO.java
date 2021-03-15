package com.infosys.product.dto;

public class BuyerDTO {


int buyerId;
String name;
String email;
String phoneNumber;
String password;
int isPrivileged;
int rewardPoints;
int isActive;
public int getBuyerId() {
	return buyerId;
}
public void setBuyerId(int buyerId) {
	this.buyerId = buyerId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getIsPrivileged() {
	return isPrivileged;
}
public void setIsPrivileged(int isPrivileged) {
	this.isPrivileged = isPrivileged;
}
public int getRewardPoints() {
	return rewardPoints;
}
public void setRewardPoints(int rewardPoints) {
	this.rewardPoints = rewardPoints;
}
public int getIsActive() {
	return isActive;
}
public void setIsActive(int isActive) {
	this.isActive = isActive;
}
}