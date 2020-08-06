package com.huynhquynh.app.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {

	@NotNull(message = "Trường này không được để trống !")
	@Email
	private String email;
	
	@NotNull(message = "Trường này không được để trống !")
	private String lastName;
	
	@NotNull(message = "Trường này không được để trống !")
	private String firstName;
	
	@NotNull(message = "Trường này không được để trống !")
	@Size(min = 8, max = 16, message = "Mật khẩu phải lớn hơn 8 và nhỏ hơn 16 kí tự")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
