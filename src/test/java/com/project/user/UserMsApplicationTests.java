package com.project.user;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;


import com.project.user.dto.BuyerDTO;
import com.project.user.dto.SellerDTO;
import com.project.user.entity.Buyer;
import com.project.user.entity.Seller;
import com.project.user.repository.BuyerRepository;
import com.project.user.repository.CartRepository;
import com.project.user.repository.SellerRepository;
import com.project.user.repository.WishlistRepository;
import com.project.user.service.BuyerService;
import com.project.user.service.CartService;
import com.project.user.service.SellerService;
import com.project.user.service.WishlistService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserMsApplicationTests {
	 	@Mock
		BuyerRepository buyerRepository;
	    
	    @Mock
	    SellerRepository sellerRepository;
	    
	    @Mock
	    WishlistRepository wishlistRepository;
		
	    @Mock
	    CartRepository cartRepository;
	    
	    @InjectMocks
	    CartService cservice= new CartService();
	    
	    @InjectMocks
	    BuyerService bservice= new BuyerService();
	    
	    @InjectMocks
	    WishlistService wservice= new WishlistService();
	    
	    @InjectMocks
	    SellerService sservice= new SellerService();
		

		@Test
		public void getAllBuyersValid() {
			Buyer buyer=new Buyer(1,"saurabh kumar","saurabh@gmail.com","7777766666","Kumar@w*3996","null","null","null");
			Buyer buyer1=new Buyer(2,"Akash kumar","akash@gmail.com","7777766555","Akash@w*3996","null","null","null");		
			
			List<Buyer> buyers = new ArrayList<>();
			buyers.add(buyer);
			buyers.add(buyer1);
			
			Mockito.when(buyerRepository.findAll()).thenReturn(buyers);
			List<BuyerDTO> actual = bservice.getAllBuyers();
			Assertions.assertEquals(buyers.isEmpty(), actual.isEmpty());
			
		}
		
		@Test
		public void getAllSellersValid() {
			Seller seller=new Seller(10,"Roshan kumar","mroshan@gmail.com","8484886578","Roshan@w*2338","Y");
			Seller seller1=new Seller(20,"Jack","jack@gmail.com","9898989898","Don@w*7777","Y");		
			
			List<Seller> sellers = new ArrayList<>();
			sellers.add(seller);
			sellers.add(seller1);
			
			Mockito.when(sellerRepository.findAll()).thenReturn(sellers);
			List<SellerDTO> actual = sservice.getAllSeller();
			Assertions.assertEquals(sellers.isEmpty(), actual.isEmpty());
			
		}
		
		@Test
		public void getBuyerByIdInValid() {
			int id =1000;			
			buyerRepository.findById(id);
		    verify(buyerRepository,times(1)).findById(1000);
		}
		
		@Test
		public void deleteBuyerByIdValid() throws Exception{
			int buyerId =1;
			bservice.deleteBuyer(buyerId);
			verify(buyerRepository, times(1)).deleteById(buyerId);
		}
		
		
		@Test
		public void deleteSellerByIdValid() throws Exception{
			int sellerid =10;
			sservice.deleteSeller(sellerid);
			verify(sellerRepository, times(1)).deleteById(sellerid);
		}
		
		@Test
		public void deleteBuyerByIdInValid() throws Exception{
			int buyerId =100;
			bservice.deleteBuyer(buyerId);
			verify(buyerRepository, times(1)).deleteById(buyerId);
		}
		
		@Test
		public void deleteSellerByIdInValid() throws Exception{
			int sellerid =1000;
			sservice.deleteSeller(sellerid);
			verify(sellerRepository, times(1)).deleteById(sellerid);
		}
		
		
		
}
