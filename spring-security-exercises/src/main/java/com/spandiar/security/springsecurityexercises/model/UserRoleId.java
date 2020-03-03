package com.spandiar.security.springsecurityexercises.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserRoleId implements Serializable {
	
	private String userName;
	private String roleName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
