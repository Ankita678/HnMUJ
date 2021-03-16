package com.project.user.dto;

import com.project.user.entity.Wishlist;


public class WishlistDTO {

	private int buyerId;
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
	@Override
	public String toString() {
		return "WishlistDTO [buyerId=" + buyerId + ", prodId=" + prodId + "]";
	}
	
	public Wishlist createEntity() {
		Wishlist wishlist = new Wishlist();
		
		wishlist.setBuyerId(this.buyerId);
		wishlist.setProdId(this.prodId);
		return wishlist;
	}
	
	public static WishlistDTO valueOf(Wishlist wishlist) {
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setBuyerId(wishlist.getBuyerId());
		wishlistDTO.setProdId(wishlist.getProdId());
		
		return wishlistDTO;
	}
}
