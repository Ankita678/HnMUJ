package com.project.user.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.user.dto.BuyerDTO;
import com.project.user.dto.BuyerLoginDTO;
import com.project.user.service.BuyerService;


@RestController
@CrossOrigin
public class BuyerController {

Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerService buyerService;
	
	@Autowired
	Environment environment;
	

	
	
	
	@PostMapping(value = "/api/buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDTO) throws Exception{
		ResponseEntity<String> response = null;
		try {
			logger.info("Registration request for buyer {}", buyerDTO);
			buyerService.createBuyer(buyerDTO);
			String successMessage = environment.getProperty("Controller.BUYER_REGISTRATION_SUCCESSFUL");
			response = new ResponseEntity<String>(successMessage,HttpStatus.CREATED);
		}catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);	 
		}
		return response;
	}
	
	@PostMapping(value = "/api/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody BuyerLoginDTO buyerLoginDTO) throws Exception {
		ResponseEntity<String> response = null;
			try {
				logger.info("Login request for customer {} with password {}", buyerLoginDTO.getEmail(),buyerLoginDTO.getPassword());
				buyerService.buyerLogin(buyerLoginDTO);
				String successMessage = environment.getProperty("Controller.BUYER_LOGIN_SUCCESSFUL");
				response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
			}catch(Exception e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);	 
			}
			return response;
	}
	
	@GetMapping(value="/api/buyers", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<BuyerDTO>> getAllBuyer() throws Exception{
		ResponseEntity<List<BuyerDTO>> response = null;
		try {
			List<BuyerDTO> buyersList = buyerService.getAllBuyers();
			response = new ResponseEntity<List<BuyerDTO>>(buyersList,HttpStatus.OK);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		
		}		
		return response;
	
	}
	
	@GetMapping(value="/api/buyers/{buyerId}", produces =MediaType.APPLICATION_JSON_VALUE)
	public BuyerDTO getByBuyerId(@PathVariable int buyerId) throws Exception{
		BuyerDTO buyer = null;
		buyer = buyerService.getByBuyerId(buyerId);
		
		return buyer;
	
	}
	
	@PostMapping(value="api/buyer/deactivate",consumes = MediaType.APPLICATION_JSON_VALUE)     
	public void deactivateBuyer(@RequestBody String email) throws Exception{  
		logger.info("Deactivating Buyer");    
		buyerService.deactivateBuyer(email);
		} 
	
	@DeleteMapping(value="/api/buyer/delete/{buyerid}")
	public String deleteBuyer(@PathVariable Integer buyerid){
		logger.info("Buyer successfully deleted with buyerid {}", buyerid);
		String successMessage = "Buyer deleted successfully !!!!!!!";
		buyerService.deleteBuyer(buyerid);
		return successMessage;
	}
	
	@PutMapping(value="/api/buyer/updateRewardPoints",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStock(@RequestBody BuyerDTO buyerdto) throws Exception{
		logger.info("Updating reward points for buyer" );
		ResponseEntity<String> response = null;
		try {
			buyerService.updateRewardPoints(buyerdto.getBuyerId(), buyerdto.getRewardPoints());
			String success_message = environment.getProperty("Controller.REWARD_POINTS_UPDATED_SUCCESSFULLY");
			response = new ResponseEntity<String>(success_message,HttpStatus.OK);
		}catch(Exception e){
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;		
		
	}
}
