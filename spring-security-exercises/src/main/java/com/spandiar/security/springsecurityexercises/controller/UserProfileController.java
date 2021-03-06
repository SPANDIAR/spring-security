package com.spandiar.security.springsecurityexercises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spandiar.security.springsecurityexercises.model.AuthenticateUserRequest;
import com.spandiar.security.springsecurityexercises.model.AuthenticateUserResponse;
import com.spandiar.security.springsecurityexercises.model.CreateUserRequestResponse;
import com.spandiar.security.springsecurityexercises.model.UserProfile;
import com.spandiar.security.springsecurityexercises.service.UserProfileService;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private AuthenticationManager authManager;
	
	@GetMapping("/about")
	public String about() {
		return "restAPI's for UserProfile";
	}
	
	@GetMapping("/{userId}")
	public UserProfile getUserDetails(@PathVariable("userId") String userId ) {
		try {

			return userProfileService.returnUserProfile(userId);
			
		} catch (Exception e) {
			 System.out.println(e.getLocalizedMessage());
			 return null;
		}
	}
	
	@PostMapping("/createuser")
	public ResponseEntity<CreateUserRequestResponse> createUser(@RequestBody CreateUserRequestResponse createUserRequest) {
		try {
				CreateUserRequestResponse userDetailFromDAO = userProfileService.createUserProfile(createUserRequest);
				return new ResponseEntity(userDetailFromDAO, HttpStatus.OK);
		}catch (Exception e) {
			CreateUserRequestResponse userDetailFromDAO = new CreateUserRequestResponse();
			userDetailFromDAO.setDetails(e.getMessage());
			return new ResponseEntity(userDetailFromDAO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateUserResponse> authenticateCredentials(@RequestBody AuthenticateUserRequest userCredentials) {
		try {
			
			Authentication authenticateResponse = authManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUserId(), userCredentials.getPassword()));
			AuthenticateUserResponse userDetails = userProfileService.loginUserDetails(authenticateResponse);
			return new ResponseEntity<AuthenticateUserResponse>(userDetails, HttpStatus.OK);
			
		} catch (Exception ex) {
			
			AuthenticateUserResponse response = new AuthenticateUserResponse();
			response.setMessage(ex.getMessage());
			
			return new ResponseEntity<AuthenticateUserResponse>(response, HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/crypto/{stringToEncrypt}")
	public String passwordCrypto(@PathVariable("stringToEncrypt") String stringToEncrypt) {
		
		return userProfileService.cryptoService(stringToEncrypt);
	}
	

}
