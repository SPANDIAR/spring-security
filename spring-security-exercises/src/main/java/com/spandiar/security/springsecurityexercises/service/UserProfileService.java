package com.spandiar.security.springsecurityexercises.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.spandiar.security.springsecurityexercises.dao.UserProfileDao;
import com.spandiar.security.springsecurityexercises.model.AuthenticateUserResponse;
import com.spandiar.security.springsecurityexercises.model.CreateUserRequestResponse;
import com.spandiar.security.springsecurityexercises.model.UserProfile;
import com.spandiar.security.springsecurityexercises.model.UserProfile.RoleUser;

@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileDao userProfileDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public CreateUserRequestResponse createUserProfile(final CreateUserRequestResponse createUserRequest) {
		
		CreateUserRequestResponse createUserResponse = new CreateUserRequestResponse();
		UserProfile createUser = enrichCreateUserProfile(createUserRequest);
		userProfileDao.createUserProfile(createUser);
		
		if(!(userProfileDao.returnUserProfile(createUserRequest.getUserId()).getUserId().isEmpty())) {
			createUserResponse.setUserId(createUserRequest.getUserId());
			createUserResponse.setDetails("Success");
		} else {
			createUserResponse.setDetails("Failed");
		};
		
		return createUserResponse;
	}
	
	public UserProfile returnUserProfile(final String userId) {
		
		UserProfile returnUserProfile = userProfileDao.returnUserProfile(userId);
		returnUserProfile.setPassword(null);
		return returnUserProfile;
	}
	
	public UserProfile enrichCreateUserProfile(final CreateUserRequestResponse createUserRequest) {
		
		UserProfile createUser = new UserProfile();
		List<RoleUser> roleUserList = new ArrayList();
		
		createUser.setUserId(createUserRequest.getUserId());
		createUser.setUserName(createUserRequest.getUserName());
		createUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
		createUser.setEmail(createUserRequest.getEmail());
		createUser.setActive(true);
		createUser.setAccountExpired(false);
		createUser.setAccountLocked(false);
		createUser.setCredentialsExpired(false);
		createUser.setLastModifiedBy("system");
		createUser.setLastModifiedDate(Calendar.getInstance());
		
		Iterator<RoleUser> iterator = createUserRequest.getRoleUser().iterator();
		
		 while (iterator.hasNext()) {
			 RoleUser next = iterator.next();
			 RoleUser roleUser = createRoleUser(createUserRequest.getUserId(), next.getRoleName());
			 roleUserList.add(roleUser);
		 }
		 
		 createUser.setRoleUser(roleUserList);
		 
		 return createUser;
	}
	
	private static RoleUser createRoleUser(String userId, String roleName) {
		
		RoleUser roleUser = new RoleUser(userId, roleName, Calendar.getInstance(), "system");
		return roleUser;
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
