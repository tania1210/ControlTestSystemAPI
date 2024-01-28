package com.app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	
	//must be final
	private String fullName;
	private String email;
	private String password;
	
	public RegistrationRequest(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}
}
