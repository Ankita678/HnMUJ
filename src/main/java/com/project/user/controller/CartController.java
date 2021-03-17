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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.project.user.dto.BuyerDTO;
import com.project.user.dto.CartDTO;
import com.project.user.dto.ProductDTO;
import com.project.user.service.BuyerService;
import com.project.user.service.CartService;


@RestController
@CrossOrigin
public class CartController {

Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Environment environment;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	BuyerService buyerService;
	
	@Value("${product.uri}")
    String productUri;
	
	@GetMapping(value = "/api/cart/view",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CartDTO>> viewCart() throws Exception{
		logger.info("View product request to Cart");
		try {
			List<CartDTO> cartList = cartService.getFromCart();
			return new ResponseEntity<>(cartList,HttpStatus.OK);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
		}
		
	}
	
	@PostMapping(value = "/api/cart/add",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addToCart(@RequestBody CartDTO cartDTO) throws Exception {
		logger.info("Add product request to cart", cartDTO.getBuyerId(),cartDTO.getProdId());
		try {
			int id = cartDTO.getProdId();			
			ProductDTO productDTO=new RestTemplate().getForObject(productUri+id, ProductDTO.class);
			BuyerDTO buyerDTO = buyerService.getByBuyerId(cartDTO.getBuyerId());
			if(buyerDTO!=null) {
			if(productDTO!=null ) {				
				cartService.addToCart(cartDTO);
				String message = environment.getProperty("Controller.PRODUCT_ADDED_SUCCESSFULLY");
				return new ResponseEntity<>(message,HttpStatus.OK);
			}else {
				String message = environment.getProperty("PRODUCT_NO_FOUND");
				return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
			}
			}else {
				String message = environment.getProperty("BUYER_NOT_FOUND");
				return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			String response = environment.getProperty("Controller.PRODUCT_NOT_FOUND");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping(value = "api/cart/remove")
	public ResponseEntity<String> removeFromCart(@RequestBody CartDTO cartDTO) {
		logger.info("Remove request from Cart");
		try {
			cartService.removeProduct(cartDTO);
			String message = "Product removed from cart Successfully!!";
			return new ResponseEntity<>(message,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/api/wishlisttocart",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> wishlistToCart(@RequestBody CartDTO cartDTO) {
		try {
			logger.info("Move product from wishlist to cart", cartDTO.getBuyerId(),cartDTO.getProdId());
			cartService.wishlistToCart(cartDTO);
			String message = "Product moved from wishlist to cart Successfully!!";
			return new ResponseEntity<>(message,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value="/api/getcart/{buyerid}")
	public ResponseEntity<List<CartDTO>> getCart(@PathVariable Integer buyerid) throws Exception{
		try{
			List<CartDTO> cartDTO = cartService.getCart(buyerid);
			return new ResponseEntity<>(cartDTO,HttpStatus.OK);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No products found in cart",e);
		}
	}
	
}
