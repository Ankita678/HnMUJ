package com.project.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.user.dto.WishlistDTO;

import com.project.user.entity.CompositePK;
import com.project.user.entity.Wishlist;
import com.project.user.repository.WishlistRepository;

@Service
public class WishlistService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	WishlistRepository wishlistRepo;
	
	
	public void addWishlist(WishlistDTO wishlistDTO) throws Exception{
		logger.info("Add product request to wishlist", wishlistDTO);
		CompositePK compKey = new CompositePK();
		compKey.setBuyerId(wishlistDTO.getBuyerId());
		compKey.setProdId(wishlistDTO.getProdId());
		Optional <Wishlist> wishlist = wishlistRepo.findById(compKey);
		if(wishlist.isPresent()) {
			throw new Exception("Wishlist.AVAILABLE");
		}else {
			Wishlist wishlist1 = wishlistDTO.createEntity();
			wishlistRepo.save(wishlist1);
		}
	}
	
	public List<WishlistDTO> allWishlist() throws Exception{
		List<Wishlist> wishlist = wishlistRepo.findAll();
		List<WishlistDTO> wishlistDTOs = new ArrayList<>();
		if(wishlist.isEmpty() == false) {
			for(Wishlist w: wishlist) {
				WishlistDTO wishlistDTO = WishlistDTO.valueOf(w);
				wishlistDTOs.add(wishlistDTO);
			}
			logger.info("wishlist details : {}", wishlistDTOs);
			return wishlistDTOs;
		}else {
			throw new Exception("Wishlist.EMPTY");
		}
	}
	
	public void removeProduct(WishlistDTO wishlistDTO) throws Exception{
		logger.info("Delete product from wishlist", wishlistDTO);
		CompositePK compKey = new CompositePK();
		compKey.setBuyerId(wishlistDTO.getBuyerId());
		compKey.setProdId(wishlistDTO.getProdId());
		Optional <Wishlist> wishlist = wishlistRepo.findById(compKey);
		if(wishlist.isPresent()) {
			wishlistRepo.deleteById(compKey);
		}else {
			throw new Exception("Wishlist.NOT_AVAILABLE");
		}
	}
	
}
