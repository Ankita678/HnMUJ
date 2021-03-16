package com.project.user.dto;

public class BuyerLoginDTO {

	
	String email;
	
	String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BuyerLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public BuyerLoginDTO() {
		super();
	}

	@Override
	public String toString() {
		return "BuyerLoginDTO [email=" + email + ", password=" + password + "]";
	}
	
	
}
