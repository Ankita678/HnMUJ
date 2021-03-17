package com.project.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
	
	@Autowired
	Environment environment;
	
	@Value("${product.uri}")
    String productUri;
	
	@PostMapping(value = "/api/wishlist/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToWishlist(@RequestBody WishlistDTO wishlistDTO) {
		try {
		logger.info("Add product request to wishlist", wishlistDTO.getBuyerId(),wishlistDTO.getProdId());
		int id = wishlistDTO.getProdId();
		
		
		ProductDTO productDTO=new RestTemplate().getForObject(productUri+id, ProductDTO.class);
			if(productDTO !=null) {
				wishlistService.addWishlist(wishlistDTO);
			}
			String message = "Product added to wishlist Successfully!!";
			return new ResponseEntity<>(message,HttpStatus.OK);
		
		}catch(Exception e) {
			return new ResponseEntity<>("No such Buyer Id and Product Id combination found",HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value = "/api/wishlist/view",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<WishlistDTO>> viewWishlist() throws Exception{
		try {
		logger.info("View product request to wishlist");
		List<WishlistDTO> wishlist =  wishlistService.allWishlist();
		return new ResponseEntity<>(wishlist,HttpStatus.OK);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
	}
	
	@DeleteMapping(value = "api/wishlist/remove")
	public ResponseEntity<String> removeFromWishlist(@RequestBody WishlistDTO wishlistDTO) throws Exception {
		try{
			logger.info("Remove from Wishlist ");		
			wishlistService.removeProduct(wishlistDTO);
			String message = "Product removed from wishlist Successfully!!";
			return new  ResponseEntity<>(message,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
}
