package com.project.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.project.user.entity.Cart;
import com.project.user.entity.CompositePK;

@Repository
public interface CartRepository extends JpaRepository<Cart, CompositePK>{
	public List<Cart> findByBuyerId(int buyerid);

}
