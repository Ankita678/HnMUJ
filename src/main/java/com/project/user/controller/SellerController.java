package com.project.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;

import com.project.user.dto.SellerDTO;
import com.project.user.dto.SellerLoginDTO;
import com.project.user.service.SellerService;

@RestController
@CrossOrigin
public class SellerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/api/seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDTO) throws Exception{
		ResponseEntity<String> response = null;
		try {
			logger.info("Registration request for seller {}", sellerDTO);
			sellerService.registerSeller(sellerDTO);
			String successMessage = environment.getProperty("Controller.SELLER_REGISTRATION_SUCCESSFUL");
			response = new ResponseEntity<>(successMessage,HttpStatus.CREATED);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);	 
		}
		return response;
	}
	
	@PostMapping(value = "/api/seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody SellerLoginDTO sellerLoginDTO) throws Exception {
		try {
			logger.info("Login request for seller {} with password {}", sellerLoginDTO.getEmail(),sellerLoginDTO.getPassword());
			sellerService.sellerLogin(sellerLoginDTO);
			String successMessage = environment.getProperty("Controller.SELLER_LOGIN_SUCCESSFUL");
			return new ResponseEntity<>(successMessage,HttpStatus.OK);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);	 
		}
	}
	
	@DeleteMapping(value="/api/seller/delete/{sellerid}")
	public ResponseEntity<String> deleteSeller(@PathVariable Integer sellerid){
		try {
			logger.info("Seller successfully deleted with sellerid {}", sellerid);
			sellerService.deleteSeller(sellerid);
			String message = "Seller deleted successfully!!";
			return new ResponseEntity<>(message,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="api/seller/deactivate",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <String> deactivateSeller(@RequestBody String email) throws Exception{
		logger.info("Deactivating Seller");
		try {
			sellerService.deactivateSeller(email);
			String message = environment.getProperty("Controller.SELLER_DEACTIVATE");
			return new ResponseEntity<>(message,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.NOT_FOUND);
			
		}
	}
	
	
}
