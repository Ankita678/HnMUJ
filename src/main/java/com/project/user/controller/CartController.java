package com.project.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.user.dto.CartDTO;
import com.project.user.dto.ProductDTO;

import com.project.user.service.CartService;


@RestController
@CrossOrigin
public class CartController {

Logger logger = LoggerFactory.getLogger(this.getClass());
	

	
	@Autowired
	CartService cartService;
	
	@GetMapping(value = "/api/cart/view",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartDTO> viewCart() {
		logger.info("View product request to Cart");
		return cartService.allCart();
	}
	
	@PostMapping(value = "/api/cart/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addToCart(@RequestBody CartDTO cartDTO) {
		logger.info("Add product request to cart", cartDTO.getBuyerId(),cartDTO.getProdId());
		
		int id = cartDTO.getProdId();
		
		String productUri = "http://localhost:8200/api/products/";
		ProductDTO productDTO=new RestTemplate().getForObject(productUri+id, ProductDTO.class);
		
		if(productDTO!=null) {
			
			cartService.addCart(cartDTO);
			String message = "Product added to cart Successfully!!";
			return message;
		}else {
			return "No such product found";
		}
		
	}
	
	@DeleteMapping(value = "api/cart/remove")
	public String removeFromCart(@RequestBody CartDTO cartDTO) {
		logger.info("Remove from Cart",cartDTO.getBuyerId(),cartDTO.getProdId());
		cartService.removeProduct(cartDTO);
		String message = "Product removed from cart Successfully!!";
		return message;
	}
	
	@PostMapping(value = "/api/wishlisttocart",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String wishlistToCart(@RequestBody CartDTO cartDTO) {
		logger.info("Move product from wishlist to cart", cartDTO.getBuyerId(),cartDTO.getProdId());
		if(cartService.wishlistToCart(cartDTO)) {
			
		String message = "Product moved from wishlist to cart Successfully!!";
		return message;
		}else {
			return "Product not available in Wishlist!!";
		}
	}
	@GetMapping(value="/api/getcart/{buyerid}")
	public List<CartDTO> getCart(@PathVariable Integer buyerid){
		List<CartDTO> cartDTO = cartService.getCart(buyerid);
		return cartDTO;
	}
	
}
