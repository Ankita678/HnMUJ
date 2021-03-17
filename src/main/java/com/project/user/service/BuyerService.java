package com.project.user.service;

import org.springframework.stereotype.Service;

import com.project.user.dto.BuyerDTO;
import com.project.user.dto.BuyerLoginDTO;

import com.project.user.entity.Buyer;

import com.project.user.repository.BuyerRepository;
import com.project.user.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


//import com.sun.org.slf4j.internal.LoggerFactory;
@Service
public class BuyerService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerRepository buyerRepo;
	
	
	
	public void registerBuyer(BuyerDTO buyerDTO) throws Exception{
		Validator.validateBuyer(buyerDTO);
		
		Buyer buyerPhone = buyerRepo.findByPhoneNumber(buyerDTO.getPhoneNumber());
		Buyer buyerEmail = buyerRepo.findByEmail(buyerDTO.getEmail());
		if(buyerPhone!=null) {
			throw new Exception("BUYER_PHONE_EXISTS");
		}
		if(buyerEmail!=null) {
			throw new Exception("BUYER_EMAIL_EXISTS");
		}
		
		logger.info("Creation request for buyer {}", buyerDTO);
		Buyer buyer = buyerDTO.CreateEntity();
		buyer.setIsActive("Y");
		buyer.setIsPrivileged("N");
		buyer.setRewardPoints("0");
		
		buyerRepo.save(buyer);
	}
	
	public boolean buyerLogin(BuyerLoginDTO buyerLoginDTO) throws Exception {
		logger.info("Login request for buyer {} with password {}", buyerLoginDTO.getEmail(),buyerLoginDTO.getPassword());
		Buyer newBuyer = buyerRepo.findByEmail(buyerLoginDTO.getEmail());
		if (newBuyer!=null) {
			if (newBuyer.getIsActive().equals("Y")) {			
				if (newBuyer.getPassword().equals(buyerLoginDTO.getPassword())) {
					return true;
				}else {
					throw new Exception("WRONG_PASSWORD");
				}
			}else {
				throw new Exception("PROFILE_INACTIVE");
			}
		}else {
			throw new Exception("WRONG_EMAILID");
		}

	}
	
	
	public List<BuyerDTO> getAllBuyers(){
		List<Buyer> buyers = buyerRepo.findAll();
		List<BuyerDTO> buyerDTOs = new ArrayList<>();
		
			for(Buyer buyer: buyers) {
				BuyerDTO buyerDTO = BuyerDTO.valueOf(buyer);
				buyerDTOs.add(buyerDTO);
			}
		logger.info("Buyer details : {}", buyerDTOs);
		return buyerDTOs;
	}
	
	public BuyerDTO getByBuyerId(int buyerId){
		Optional<Buyer> buyer = buyerRepo.findById(buyerId);
		Buyer buyerEntity = null;
		if(buyer.isPresent()) {
			buyerEntity = buyer.get();
		}
		BuyerDTO buyerdto = BuyerDTO.valueOf(buyerEntity);
		return buyerdto;
	}
	
	public void deactivateBuyer(String email) throws Exception{
		Buyer buyer = buyerRepo.findByEmail(email);
		if(buyer!=null) {
			buyer.setIsActive("N");
			buyerRepo.save(buyer);
		}else {
			throw new Exception("WRONG_EMAILID");
		}
	}
	
	public void deleteBuyer(int buyerId) throws Exception{
		Optional<Buyer> buyer = buyerRepo.findById(buyerId);
		if(buyer.isPresent()) {
			buyerRepo.deleteById(buyerId);
		}else {
			throw new Exception("BUYER_NOT_FOUND");
		}
	}
	
	public void updateRewardPoints(int buyerid, String rewardPoints) throws Exception {
		logger.info("Update reward points request for buyer {}", buyerid);
		Optional<Buyer> optBuyer = buyerRepo.findById(buyerid);
		Buyer buyer = null;
		if(optBuyer.isPresent()) {			
			buyer = optBuyer.get();
			int rp = Integer.parseInt(rewardPoints);
			if(rp > 10000) {
				buyer.setIsPrivileged("Y");
			}else {
				buyer.setIsPrivileged("N");
			}
			buyer.setRewardPoints(rewardPoints);
			buyerRepo.save(buyer);
		}else {
			throw new Exception("BUYER_NOT_FOUND");
		}
		
	}

}
