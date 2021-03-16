package com.project.user.dto;


import com.project.user.entity.Seller;

public class SellerDTO {

	int sellerId;
	String name;
	String email;
	String phoneNumber;
	String password;
	String isActive;
	
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	public String toString() {
		return "SellerDTO [sellerId = " + sellerId + ", name = " + name + ",email = " + email
				+ ",phoneNumber = " + phoneNumber + ", password = " + password 
				+ ",isActive = " + isActive + "]";
	}
	
	public static SellerDTO valueOf(Seller seller) {
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setSellerId(seller.getSellerId());
		sellerDTO.setName(seller.getName());
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setPhoneNumber(seller.getPhoneNumber());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setIsActive(seller.getIsActive());
		
		return sellerDTO;
		
	}
	
	public Seller CreateEntity() {

		Seller seller = new Seller();
		seller.setSellerId(this.sellerId);
		seller.setEmail(this.email);
		seller.setName(this.name);
		seller.setPhoneNumber(this.phoneNumber);
		seller.setPassword(this.password);
		seller.setIsActive(this.isActive);
		
		
		return seller;
	}
}
