package com.huynhquynh.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huynhquynh.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.huynhquynh.app.ws.ui.model.request.UserDetailsRequestModel;
import com.huynhquynh.app.ws.ui.model.responce.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	Map<String, UserRest> users;

	@GetMapping()
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "page: " + page + " and limit: " + limit + " and sort: " + sort;
	}

//	Chấp nhận xử lý theo 2 dạng : XML và JSON
//	Nếu định dạng muốn trả về là XML thì cài thêm dependencies json fommat xml từ maven
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		if (users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}

//	Phương thức có thể do người dùng tạo hoặc nhà phát triển tạo 
	@PostMapping(consumes = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE 
				}, 
				produces = {
						MediaType.APPLICATION_XML_VALUE, 
						MediaType.APPLICATION_JSON_VALUE 
				})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserRest userRest = new UserRest();
		userRest.setEmail(userDetails.getEmail());
		userRest.setFirstName(userDetails.getFirstName());
		userRest.setLastName(userDetails.getLastName());
		userRest.setPassword(userDetails.getPassword());
		String userId = UUID.randomUUID().toString();
		userRest.setId(userId);
		if (users == null) {
			users = new HashMap<String, UserRest>();			
		}
		users.put(userId, userRest);
		return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}", 
				consumes = { 
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE 
				}, 
				produces = {
						MediaType.APPLICATION_XML_VALUE, 
						MediaType.APPLICATION_JSON_VALUE 
				})
	public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetails) {
		UserRest userRest = users.get(userId);
		userRest.setFirstName(updateUserDetails.getFirstName());
		userRest.setLastName(updateUserDetails.getLastName());
		users.put(userId, userRest);
		return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user ";
	}
}
