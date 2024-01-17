package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"username", "password"})
public class User {

	@JsonProperty("id")
	   private Integer id;
	   
	   @JsonProperty("username")
	   private String username;
	   
	   @JsonProperty("email")
	   private String email;
	   
	   @JsonProperty("password")
	   private String password;

//	    @Enumerated(EnumType.STRING)
	   private Role role;
	
	
	public User(String email, String password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
