package com.app.dto;

import com.app.security.Role;

public class UserDTO {
	
	private Long id;
	private String firstName;
	private String LastName;
	private String email;
	private Role role;
	
	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Role getRole() {
		return role;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
}
