package com.project.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.user.dto.SellerDTO;
import com.project.user.dto.SellerLoginDTO;
import com.project.user.service.SellerService;

@RestController
@CrossOrigin
public class SellerController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SellerService sellerService;
	
	@PostMapping(value = "/api/seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registerSeller(@RequestBody SellerDTO sellerDTO) throws Exception{
		logger.info("Registration request for seller {}", sellerDTO);
		sellerService.createSeller(sellerDTO);
	}
	
	@PostMapping(value = "/api/seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody SellerLoginDTO sellerLoginDTO) throws Exception {
		logger.info("Login request for Seller {} with password {}", sellerLoginDTO.getEmail(),sellerLoginDTO.getPassword());
		if(sellerService.sellerLogin(sellerLoginDTO)) {
			return "Successful";
		}
		return "Fail";
	}
	
	@DeleteMapping(value="/api/seller/delete/{sellerid}")
	public String deleteSeller(@PathVariable Integer sellerid){
		logger.info("Seller successfully deleted with sellerid {}", sellerid);
		
		String uri = "http://localhost:8200/api/products/seller/"+sellerid;
		String response=new RestTemplate().getForObject(uri, String.class);
		
		
		String successMessage = "Seller deleted successfully !!!!!!!";
		
		sellerService.deleteSeller(sellerid);
		String message = successMessage + response + sellerid;
		return message;
	}
	
	@PostMapping(value="api/seller/deactivate",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deactivateSeller(@RequestBody String email) throws Exception{
		logger.info("Deactivating Seller");
		sellerService.deactivateSeller(email);
		
	}
	
	
}
