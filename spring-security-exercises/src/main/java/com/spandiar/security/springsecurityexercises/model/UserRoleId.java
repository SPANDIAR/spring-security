package com.spandiar.security.springsecurityexercises.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserRoleId implements Serializable {
	
	private String userId;
	private String roleName;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserRoleId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
