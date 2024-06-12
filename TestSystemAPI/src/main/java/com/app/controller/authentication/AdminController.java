package com.app.controller.authentication;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.service.authentication.AdminService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AdminController {

	private AdminService adminService;

	@GetMapping
	public List<UserDTO> getAllUsers() {

		return adminService.getAllUsers();
	}
		
	@PutMapping
	public ResponseEntity<Void> updateUser(Long id, Map<String, Object> updateData) {
		try {
			adminService.updateUser(id, updateData);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok().build();

		
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteUser(Long id) {
		try {
			adminService.deleteUser(id);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.ok().build();

	
	}

	/*add user
	grand user to teacher(add subject)/admin*/


	/*add subject
	* update subject
	* delete subject*/
	
	
	
	
}
