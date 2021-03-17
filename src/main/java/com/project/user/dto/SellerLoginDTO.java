package com.project.user.dto;

public class SellerLoginDTO {
	
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

	public SellerLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public SellerLoginDTO() {
		super();
	}

	@Override
	public String toString() {
		return "SellerLoginDTO [email=" + email + ", password=" + password + "]";
	}
}
