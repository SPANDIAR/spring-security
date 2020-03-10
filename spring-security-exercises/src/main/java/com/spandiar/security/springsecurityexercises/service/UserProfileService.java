package com.spandiar.security.springsecurityexercises.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.spandiar.security.springsecurityexercises.dao.UserProfileDao;
import com.spandiar.security.springsecurityexercises.model.AuthenticateUserResponse;
import com.spandiar.security.springsecurityexercises.model.UserProfile;

@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileDao userProfileDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ApplicationUserDetails appUserDetails;
	
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
		createUserRequest.setLastModifiedBy("system");
		createUserRequest.setLastModifiedDate(Calendar.getInstance());
		createUserRequest.getRoleUser().forEach(user -> user.setUserId(createUserRequest.getUserId()));
		createUserRequest.getRoleUser().forEach(user->user.setLastModifiedBy("system"));
		createUserRequest.getRoleUser().forEach(user->user.setLastModifiedDate(Calendar.getInstance()));
		
	}
	
	public String cryptoService(String stringToEncrypt) {
		String encryptedString = passwordEncoder.encode(stringToEncrypt);
		return (stringToEncrypt + " - " + encryptedString + " - " + passwordEncoder.getClass().getName());
	}
	
	public AuthenticateUserResponse loginUserDetails(Authentication authenticateResponse) {
		
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		GetUserDetails userDetails = new GetUserDetails();
		AuthenticateUserResponse loginUserDetails = new AuthenticateUserResponse();
		userDetails = (GetUserDetails) authenticateResponse.getPrincipal();
		
		loginUserDetails.setUsername(userDetails.getUsername());
		loginUserDetails.setAuthorities(userDetails.getAuthorities());
		loginUserDetails.setMessage("Successful login - " + sessionId);
		
		return loginUserDetails;
		
	}

}
