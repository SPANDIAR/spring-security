package com.spandiar.security.springsecurityexercises.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spandiar.security.springsecurityexercises.dao.UserProfileDao;
import com.spandiar.security.springsecurityexercises.model.UserProfile;

@Service
public class ApplicationUserDetails implements UserDetailsService{
	
	private GetUserDetails userDetails;
	@Autowired
	private UserProfileDao userProfileDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserProfile returnUserProfile = userProfileDao.returnUserProfile(username);
		
		userDetails = new GetUserDetails();
		return userDetails.GetUserDetailsByUserName(returnUserProfile);
	}

}
