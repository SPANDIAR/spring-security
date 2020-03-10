package com.spandiar.security.springsecurityexercises.model;

public class AuthenticateUserRequest {
	
	private String userId;
	private String password;
	
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}
	
	public AuthenticateUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
