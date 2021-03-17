package com.project.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.dto.CartDTO;

import com.project.user.entity.Cart;
import com.project.user.entity.CompositePK;
import com.project.user.entity.Wishlist;
import com.project.user.repository.CartRepository;
import com.project.user.repository.WishlistRepository;

@Service
public class CartService {

Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	WishlistRepository wishlistRepo;
	
	public void addToCart(CartDTO cartDTO) {
		logger.info("Add product request to cart", cartDTO);
		Cart cart = cartDTO.createEntity();
		cartRepo.save(cart);
	}
	
	public List<CartDTO> getFromCart() throws Exception{
		List<Cart> cart = cartRepo.findAll();
		List<CartDTO> cartDTOs = new ArrayList<>();
		if(cart.isEmpty()==false) {
			for(Cart c: cart) {
				CartDTO cartDTO = CartDTO.valueOf(c);
				cartDTOs.add(cartDTO);
			}
		logger.info("Cart details : {}", cartDTOs);
		return cartDTOs;
		}else {
			throw new Exception ("CART_EMPTY");
		}
	}
	
	public void removeProduct(CartDTO cartDTO) throws Exception {
		logger.info("Delete product from cart", cartDTO);
		CompositePK compKey = new CompositePK();
		compKey.setBuyerId(cartDTO.getBuyerId());
		compKey.setProdId(cartDTO.getProdId());
		Optional <Cart> cart = cartRepo.findById(compKey);
		if(cart.isPresent()) {
			cartRepo.deleteById(compKey);
		}else {
			throw new Exception("PRODUCT_NOT_FOUND");
		}
	}
	
	public boolean wishlistToCart(CartDTO cartDTO) throws Exception{
		logger.info("Move Product from cart", cartDTO);
		CompositePK compKey = new CompositePK();
		compKey.setBuyerId(cartDTO.getBuyerId());
		compKey.setProdId(cartDTO.getProdId());
		Optional <Wishlist> wishlist = wishlistRepo.findById(compKey);
		if(wishlist.isPresent()) {
			Cart cart = cartDTO.createEntity();
			cartRepo.save(cart);
			wishlistRepo.deleteById(compKey);
			return true;
		}else {
			throw new Exception("NO_PRODUCT_FOUND");
		}
	}
	
	public List<CartDTO> getCart(int buyerid) throws Exception{
		List<Cart> cart = cartRepo.findByBuyerId(buyerid);
		List<CartDTO> cartDTO = new ArrayList<>();
		if(cart.isEmpty() == false) {
			for(Cart c:cart) {
				CartDTO cartD = CartDTO.valueOf(c);
				cartDTO.add(cartD);
			}		
			return cartDTO;
		}else {
			throw new Exception("PRODUCT_NOT_FOUND");
		}
	}
	
}
