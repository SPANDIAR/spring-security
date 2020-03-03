package com.spandiar.security.springsecurityexercises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spandiar.security.springsecurityexercises.model.UserProfile;
import com.spandiar.security.springsecurityexercises.service.UserProfileService;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfileService;
	
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
	public void createUser(@RequestBody UserProfile createUserRequest) {
		try {
			userProfileService.createUserProfile(createUserRequest);
		}catch (Exception e) {
			System.out.println("Error");
		}
		
	}
	
	@GetMapping("/crypto/{stringToEncrypt}")
	public String passwordCrypto(@PathVariable("stringToEncrypt") String stringToEncrypt) {
		
		return userProfileService.cryptoService(stringToEncrypt);
	}

}