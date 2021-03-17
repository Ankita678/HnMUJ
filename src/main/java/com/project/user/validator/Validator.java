package com.project.user.validator;

import com.project.user.dto.BuyerDTO;
import com.project.user.dto.SellerDTO;

public class Validator {

	public static void validateBuyer(BuyerDTO buyer) throws Exception {
		if(!validateName(buyer.getName())) {
			throw new Exception("Validator.INVALID_NAME");
		}
		if(!validateEmailId(buyer.getEmail())) {
			throw new Exception("Validator.INVALID_EMAIL_ID");
		}
		if(!validatePhonenumber(buyer.getPhoneNumber())) {
			throw new Exception("Validator.INVALID_PHONE_NO");
		}
		if(!validatePassword(buyer.getPassword())) {
			throw new Exception("Validator.INVALID_PASSWORD");
		}
	}
	public static void validateSeller(SellerDTO seller) throws Exception{
		if(!validateName(seller.getName())) {
			throw new Exception("Validator.INVALID_NAME");
		}
		if(!validateEmailId(seller.getEmail())) {
			throw new Exception("Validator.INVALID_EMAIL_ID");
		}
		if(!validatePhonenumber(seller.getPhoneNumber())) {
			throw new Exception("Validator.INVALID_PHONE_NO");
		}
		if(!validatePassword(seller.getPassword())) {
			throw new Exception("Validator.INVALID_PASSWORD");
		}
	
	}
	public static Boolean validateName(String name) {
		String regex = "[A-Za-z]+(\\s[A-Za-z]+)*";
		if(name.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validateEmailId(String emailId) {
		String regex = "[A-Za-z]+@[A-Za-z]+\\.com";
		if(emailId.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validatePhonenumber(String phonenumber) {
		String regex = "[6-9]{1}[0-9]{9}";
		if(phonenumber.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validatePassword(String password) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$^&*]).{7,20}$";
		if(password.matches(regex)) {
			return true;
		}
		return false;
	}
}
