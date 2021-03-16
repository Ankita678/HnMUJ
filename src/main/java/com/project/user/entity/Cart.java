package com.project.user.entity;



import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="cart")
@IdClass(CompositePK.class)
public class Cart {

	@Id
	@Column(name = "BUYERID",nullable = false)
	int buyerId;
	@Id
	@Column(name = "PRODID",nullable = false)
	int prodId;
	@Column(name = "QUANTITY",nullable = false)
	int quantity;
	public Cart(int buyerId, int prodId, int quantity) {
		super();
		this.buyerId = buyerId;
		this.prodId = prodId;
		this.quantity = quantity;
	}
	public Cart() {
		super();
	}
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
