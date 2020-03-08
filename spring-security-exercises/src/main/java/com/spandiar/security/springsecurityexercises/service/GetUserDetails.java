package com.spandiar.security.springsecurityexercises.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.spandiar.security.springsecurityexercises.dao.UserProfileDao;
import com.spandiar.security.springsecurityexercises.model.UserProfile;

public class GetUserDetails implements UserDetails{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Collection<GrantedAuthority> userAuthority = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userAuthority;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public UserDetails GetUserDetailsByUserName(UserProfile returnUserProfile) {
		
		this.username = returnUserProfile.getUserName();
		this.password = returnUserProfile.getPassword();
		this.accountNonExpired = !(returnUserProfile.isAccountExpired());
		this.accountNonLocked = !(returnUserProfile.isAccountLocked());
		this.credentialsNonExpired = !(returnUserProfile.isCredentialsExpired());
		this.enabled = returnUserProfile.isActive();
		this.userAuthority = returnUserProfile.getRoleUser().stream()
				.map(user -> user.getRoleName())
				.map(role -> ("ROLE_"+ role))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		
		return this;
		
	}

	public GetUserDetails() {
		
	}

}
