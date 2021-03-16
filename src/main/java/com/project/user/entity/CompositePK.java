package com.project.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CompositePK implements Serializable{

	@Column(name="BUYERID",nullable=false)
	private int buyerId;
	@Column(name="PRODID",nullable=false)
	private int prodId;
	
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	
	
}
