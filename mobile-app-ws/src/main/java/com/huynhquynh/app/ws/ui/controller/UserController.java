package com.huynhquynh.app.ws.ui.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.huynhquynh.app.ws.usersevice.UserSevice;

@RestController
@RequestMapping("users")
public class UserController {
	
	Map<String, UserRest> users;
	
	@Autowired
	UserSevice userSevice;

	@GetMapping()
	public ResponseEntity<List<UserRest>> getUsers( @RequestParam(value = "page", defaultValue = "1") int page,
							@RequestParam(value = "limit", defaultValue = "50") int limit,
							@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		
		return new ResponseEntity<List<UserRest>>(userSevice.getUsers(page, limit, sort), HttpStatus.OK);
	}

//	Chấp nhận xử lý theo 2 dạng : XML và JSON
//	Nếu định dạng muốn trả về là XML thì cài thêm dependencies json fommat xml từ maven
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
//		if(true) throw new UserSeviceException("Lỗi ở đây");
		UserRest userRest = userSevice.getUser(userId);
		if (userRest != null) {
			return new ResponseEntity<>(userRest, HttpStatus.OK);
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
		
		UserRest userRest = userSevice.createUser(userDetails);
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
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetails) {
	
		return userSevice.updateUser(userId, updateUserDetails);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		userSevice.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
