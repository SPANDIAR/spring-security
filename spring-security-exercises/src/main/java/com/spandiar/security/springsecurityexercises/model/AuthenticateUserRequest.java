package com.spandiar.security.springsecurityexercises.model;

public class AuthenticateUserRequest {
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public AuthenticateUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
