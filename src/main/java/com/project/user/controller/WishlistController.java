package com.project.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import com.project.user.dto.ProductDTO;
import com.project.user.dto.WishlistDTO;
import com.project.user.service.CartService;
import com.project.user.service.WishlistService;

@RestController
@CrossOrigin
public class WishlistController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WishlistService wishlistService;
	
	@Autowired
	CartService cartService;
	
	
	@PostMapping(value = "/api/wishlist/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addToWishlist(@RequestBody WishlistDTO wishlistDTO) {
		logger.info("Add product request to wishlist", wishlistDTO.getBuyerId(),wishlistDTO.getProdId());
		int id = wishlistDTO.getProdId();
		
		String productUri = "http://localhost:8200/api/products/";
		ProductDTO productDTO=new RestTemplate().getForObject(productUri+id, ProductDTO.class);
		
		if(productDTO!=null) {
			wishlistService.addWishlist(wishlistDTO);
			String message = "Product added to wishlist Successfully!!";
			return message;
		}else {
			return "No such product found";
		}
		
	}
	
	@GetMapping(value = "/api/wishlist/view",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WishlistDTO> viewWishlist() {
		logger.info("View product request to wishlist");
		return wishlistService.allWishlist();
	}
	
	@DeleteMapping(value = "api/wishlist/remove")
	public String removeFromWishlist(@RequestBody WishlistDTO wishlistDTO) throws Exception {
		logger.info("Remove from Wishlist",wishlistDTO.getBuyerId(),wishlistDTO.getProdId());
		wishlistService.removeProduct(wishlistDTO);
		String message = "Product removed from wishlist Successfully!!";
		return message;
	}
}
