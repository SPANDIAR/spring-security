package com.spandiar.security.springsecurityexercises.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spandiar.security.springsecurityexercises.dao.UserProfileDao;
import com.spandiar.security.springsecurityexercises.model.AuthenticateUserRequest;
import com.spandiar.security.springsecurityexercises.model.AuthenticateUserResponse;
import com.spandiar.security.springsecurityexercises.model.UserProfile;

@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileDao userProfileDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void createUserProfile(final UserProfile createUserRequest) {
		
		enrichCreateUserProfile(createUserRequest);
		userProfileDao.createUserProfile(createUserRequest);
		
	}
	
	public UserProfile returnUserProfile(final String userId) {
		
		UserProfile returnUserProfile = userProfileDao.returnUserProfile(userId);
		returnUserProfile.setPassword(null);
		return returnUserProfile;
	}
	
	public void enrichCreateUserProfile(final UserProfile createUserRequest) {
		
		createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		createUserRequest.setActive(true);
		createUserRequest.setAccountExpired(false);
		createUserRequest.setAccountLocked(false);
		createUserRequest.setCredentialsExpired(false);
		createUserRequest.setPasswordExpired(false);
		createUserRequest.setLastModifiedBy("system");
		createUserRequest.setLastModifiedDate(Calendar.getInstance());
		createUserRequest.getRoleUser().forEach(user -> user.setUserName(createUserRequest.getUserName()));
		createUserRequest.getRoleUser().forEach(user->user.setLastModifiedBy("system"));
		createUserRequest.getRoleUser().forEach(user->user.setLastModifiedDate(Calendar.getInstance()));
		
	}
	
	public String cryptoService(String stringToEncrypt) {
		String encryptedString = passwordEncoder.encode(stringToEncrypt);
		return (stringToEncrypt + " - " + encryptedString + " - " + passwordEncoder.getClass().getName());
	}
	
	public AuthenticateUserResponse loginUserDetails(Authentication authenticateResponse) {
		
		GetUserDetails userDetails = new GetUserDetails();
		AuthenticateUserResponse loginUserDetails = new AuthenticateUserResponse();
		userDetails = (GetUserDetails) authenticateResponse.getPrincipal();
		
		loginUserDetails.setUsername(userDetails.getUsername());
		loginUserDetails.setAuthorities(userDetails.getAuthorities());
		loginUserDetails.setMessage("Successful login");
		
		return loginUserDetails;
		
	}

}
