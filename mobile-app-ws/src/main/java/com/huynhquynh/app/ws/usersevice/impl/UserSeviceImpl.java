package com.huynhquynh.app.ws.usersevice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huynhquynh.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.huynhquynh.app.ws.ui.model.request.UserDetailsRequestModel;
import com.huynhquynh.app.ws.ui.model.responce.UserRest;
import com.huynhquynh.app.ws.usersevice.UserSevice;
import com.huynhquynh.app.ws.utils.Utils;

@Service
public class UserSeviceImpl implements UserSevice {
	
	Map<String, UserRest> users;
	
	Utils utils;
	
	public UserSeviceImpl () {}
	
	@Autowired
	public UserSeviceImpl(Utils utils) {
		this.utils = utils;
	}

	@Override
	public List<UserRest> getUsers(Integer page, Integer limit, String sort) {		
		return new ArrayList<UserRest>(users.values());
	}

	@Override
	public UserRest getUser(String userId) {
		
		if (users.containsKey(userId)) {
			return users.get(userId);
		} 
		return null;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest userRest = new UserRest();
		userRest.setEmail(userDetails.getEmail());
		userRest.setFirstName(userDetails.getFirstName());
		userRest.setLastName(userDetails.getLastName());
		userRest.setPassword(userDetails.getPassword());
		
		String userId = utils.generateID();		
		userRest.setId(userId);
		
		if (users == null) {
			users = new HashMap<String, UserRest>();			
		}
		
		users.put(userId, userRest);
		if(users.put(userId, userRest) != null) return userRest;
		return null;
	}

	@Override
	public UserRest updateUser(String userId, UpdateUserDetailsRequestModel updateUserDetails) {
		UserRest userRest = users.get(userId);
		userRest.setFirstName(updateUserDetails.getFirstName());
		userRest.setLastName(updateUserDetails.getLastName());
		if(users.put(userId, userRest) != null) return userRest;
		return null;
	}

	@Override
	public void deleteUser(String userId) {		
		users.remove(userId);
	}

}
