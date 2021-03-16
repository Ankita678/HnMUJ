package com.project.user.dto;

import com.project.user.entity.Cart;

public class CartDTO {

	private int buyerId;
	private int prodId;
	private int quantity;
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
	@Override
	public String toString() {
		return "CartDTO [buyerId=" + buyerId + ", prodId=" + prodId + ", quantity=" + quantity + "]";
	}
	
	public Cart createEntity() {
		
		Cart cart = new Cart();
		cart.setBuyerId(this.buyerId);
		cart.setProdId(this.prodId);
		cart.setQuantity(this.quantity);
		return cart;
	}
	
	public static CartDTO valueOf(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setBuyerId(cart.getBuyerId());
		cartDTO.setProdId(cart.getProdId());
		cartDTO.setQuantity(cart.getQuantity());
		
		return cartDTO;
	}
}
