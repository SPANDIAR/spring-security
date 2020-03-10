package com.spandiar.security.springsecurityexercises.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name="USERPROFILE")
@JsonInclude(Include.NON_NULL)
public class UserProfile {
	
	@Id
	@Column(name="USERID")
	private String userId;
	@Column(name="USERNAME")
	private String userName;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="EMAIL")
	private String email;
	@Column(name="ISACTIVE")
	private boolean isActive;
	@Column(name="ISACCOUNTEXPIRED")
	private boolean isAccountExpired;
	@Column(name="ISACCOUNTLOCKED")
	private boolean isAccountLocked;
	@Column(name="ISCREDENTIALSEXPIRED")
	private boolean isCredentialsExpired;
	@Column(name="LASTMODIFIEDDATE")
	private Calendar lastModifiedDate;
	@Column(name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name="USERID")
	private List<roleUser> roleUser;
	
	@Entity
	@Table(name="ROLEUSER")
	@IdClass(UserRoleId.class)
	public static class roleUser {
		@Id
		@Column(name="USERID")
		private String userId;
		@Id
		@Column(name="ROLENAME")
		private String roleName;
		@Column(name="LASTMODIFIEDDATE")
		@JsonIgnore
		private Calendar lastModifiedDate;
		@Column(name="LASTMODIFIEDBY")
		@JsonIgnore
		private String lastModifiedBy;
		
		
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
		public Calendar getLastModifiedDate() {
			return lastModifiedDate;
		}
		public void setLastModifiedDate(Calendar lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}
		public String getLastModifiedBy() {
			return lastModifiedBy;
		}
		public void setLastModifiedBy(String lastModifiedBy) {
			this.lastModifiedBy = lastModifiedBy;
		}
		public roleUser() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isAccountExpired() {
		return isAccountExpired;
	}

	public void setAccountExpired(boolean isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
	}

	public boolean isAccountLocked() {
		return isAccountLocked;
	}

	public void setAccountLocked(boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public boolean isCredentialsExpired() {
		return isCredentialsExpired;
	}

	public void setCredentialsExpired(boolean isCredentialsExpired) {
		this.isCredentialsExpired = isCredentialsExpired;
	}

	public Calendar getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Calendar lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public List<roleUser> getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(List<roleUser> roleUser) {
		this.roleUser = roleUser;
	}

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
