package com.project.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.project.user.dto.SellerDTO;
import com.project.user.dto.SellerLoginDTO;
import com.project.user.entity.Seller;
import com.project.user.repository.SellerRepository;
import com.project.user.validator.Validator;

@Service
public class SellerService {

Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SellerRepository sellerRepo;
	
	public void registerSeller(SellerDTO sellerDTO) throws Exception {
		Validator.validateSeller(sellerDTO);
		Seller sellerPhone = sellerRepo.findByPhoneNumber(sellerDTO.getPhoneNumber());
		Seller sellerEmail = sellerRepo.findByEmail(sellerDTO.getEmail());
		if(sellerPhone!=null) {
			throw new Exception("SELLER_PHONE_EXISTS");
		}
		if(sellerEmail!=null) {
			throw new Exception("SELLER_EMAIL_EXISTS");
		}
		logger.info("Creation request for seller {}", sellerDTO);
		Seller seller = sellerDTO.CreateEntity();
		seller.setIsActive("Y");
		sellerRepo.save(seller);
	}
	
	public boolean sellerLogin(SellerLoginDTO sellerLoginDTO) throws Exception{
		logger.info("Login request for seller {}", sellerLoginDTO);
		Seller newSeller = sellerRepo.findByEmail(sellerLoginDTO.getEmail());
		if (newSeller!=null) {
			if (newSeller.getIsActive().equals("Y")) {			
				if (newSeller.getPassword().equals(sellerLoginDTO.getPassword())) {
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
	
	public List<SellerDTO> getAllSeller(){
		List<Seller> sellers = sellerRepo.findAll();
		List<SellerDTO> sellerDTOs = new ArrayList<>();
		
			for(Seller seller: sellers) {
				SellerDTO sellerDTO = SellerDTO.valueOf(seller);
				sellerDTOs.add(sellerDTO);
			}
		logger.info("Seller details : {}", sellerDTOs);
		return sellerDTOs;
	}
	
	public void deactivateSeller(String email) throws Exception{
		Seller seller = sellerRepo.findByEmail(email);
		if(seller!=null) {
			seller.setIsActive("N");
			sellerRepo.save(seller);
		}else {
			throw new Exception("WRONG_EMAILID");
		}
	}
	
	public void deleteSeller(int sellerid) throws Exception {
		Optional<Seller> seller = sellerRepo.findById(sellerid);
		if(seller.isPresent()) {
			sellerRepo.deleteById(sellerid);
		}else {
			throw new Exception("SELLER_NOT_FOUND");
		}
	}
}
