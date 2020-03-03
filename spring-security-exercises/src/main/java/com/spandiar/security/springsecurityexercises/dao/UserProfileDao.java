package com.spandiar.security.springsecurityexercises.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spandiar.security.springsecurityexercises.model.UserProfile;

@Repository
public class UserProfileDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public void createUserProfile(final UserProfile createUserRequest) {
		
		entityManager.persist(createUserRequest);
		
	}
	
	public UserProfile returnUserProfile(final String userId) {
		
		return entityManager.find(UserProfile.class, userId);
		
	}

}
