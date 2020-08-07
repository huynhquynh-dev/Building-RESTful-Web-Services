package com.huynhquynh.app.ws.usersevice;

import java.util.List;

import com.huynhquynh.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.huynhquynh.app.ws.ui.model.request.UserDetailsRequestModel;
import com.huynhquynh.app.ws.ui.model.responce.UserRest;

public interface UserSevice {
	
	List<UserRest> getUsers(Integer page, Integer limit, String sort);
	
	UserRest getUser(String userId);

	UserRest createUser(UserDetailsRequestModel userDetails);
	
	UserRest updateUser(String userId, UpdateUserDetailsRequestModel updateUserDetails);
	
	void deleteUser(String userId);
}
