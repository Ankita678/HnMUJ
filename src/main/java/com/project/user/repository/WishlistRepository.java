package com.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.user.entity.CompositePK;
import com.project.user.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, CompositePK>{

}
