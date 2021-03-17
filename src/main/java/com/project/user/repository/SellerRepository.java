package com.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.user.entity.Seller;
@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

	public Seller findByPhoneNumber(String phoneNumber);
	public Seller findByEmail(String email);
}
