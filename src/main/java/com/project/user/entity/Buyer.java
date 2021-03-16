package com.project.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "buyer")
public class Buyer {
	
	@Id
	@Column(name="BUYERID",nullable=false, length=11)
	int buyerId;
	
	@Column(name="NAME",nullable=false, length=45)
	String name;
	
	@Column(name="EMAIL",nullable=false, length=45)
	String email;
	
	@Column(name="PHONENUMBER",nullable=false, length=45)
	String phoneNumber;
	
	@Column(name="PASSWORD",nullable=false, length=45)
	String password;
	
	@Column(name="ISPRIVILEGED", length=1)
	String isPrivileged;
	
	@Column(name="REWARDPOINTS", length=11)
	String rewardPoints;
	
	@Column(name="ISACTIVE", length=1)
	String isActive;
	
	public Buyer(){
		super();
	}
	
	public Buyer(String name, String email, String phoneNumber, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public Buyer(int buyerId, String name, String email, String phoneNumber, String password, 
			String isPrivileged, String rewardPoints, String isActive) {
		super();
		this.buyerId = buyerId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.isPrivileged = isPrivileged;
		this.rewardPoints = rewardPoints;
		this.isActive = isActive;
	}

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

	public String getIsPrivileged() {
		return isPrivileged;
	}

	public void setIsPrivileged(String isPrivileged) {
		this.isPrivileged = isPrivileged;
	}

	public String getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(String rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
}
