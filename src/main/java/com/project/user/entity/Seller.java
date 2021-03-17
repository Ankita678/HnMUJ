package com.project.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
	
	@Id
	@Column(name="SELLERID",nullable=false)
	int sellerId;
	
	@Column(name="NAME",nullable=false)
	String name;
	
	@Column(name="EMAIL",nullable=false)
	String email;
	
	@Column(name="PHONENUMBER",nullable=false)
	String phoneNumber;
	
	@Column(name="PASSWORD",nullable=false)
	String password;
	
	@Column(name="ISACTIVE",nullable=false)
	String isActive;

	
	public Seller() {
		super();
	}
	
	public Seller(String name, String email, String phoneNumber, String password) {
		super();
		
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public Seller(int sellerId, String name, String email, String phoneNumber, String password, String isActive) {
		super();
		
		this.sellerId = sellerId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.isActive = isActive;
	}

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
	
	
}
