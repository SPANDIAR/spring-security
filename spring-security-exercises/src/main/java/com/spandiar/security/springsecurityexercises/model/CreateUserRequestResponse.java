package com.spandiar.security.springsecurityexercises.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.spandiar.security.springsecurityexercises.model.UserProfile.RoleUser;

@JsonInclude(Include.NON_NULL)
public class CreateUserRequestResponse {
	
	private String userId;
	private String userName;
	private String password;
	private String email;
	private String details;
	private List<RoleUser> roleUser;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<RoleUser> getRoleUser() {
		return roleUser;
	}
	public void setRoleUser(List<RoleUser> roleUser) {
		this.roleUser = roleUser;
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public CreateUserRequestResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
