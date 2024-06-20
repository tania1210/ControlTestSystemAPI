package com.app.controller.authentication;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.UserDTO;
import com.app.service.authentication.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/admin")
@AllArgsConstructor
public class AdminController {

	private AdminService adminService;

	@Operation(summary = "отримати всіх користувачів", description = "повертає список обʼєктів")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "запит виконано успішно")
	})
	@GetMapping
	public List<UserDTO> getAllUsers() {

		return adminService.getAllUsers();
	}

	@Operation(summary = "змінити рівень доступу користувача", description = "оновлює роль користувача")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "роль успішно змінено"),
			@ApiResponse(responseCode = "400", description = "невірна роль"),
			@ApiResponse(responseCode = "404", description = "користувача не знайдено")
	})
	@PatchMapping
	public ResponseEntity<?> updateUserRole(Long id, String role) {
		try {
			adminService.updateUserRole(id, role);
			return ResponseEntity.ok("role changed");
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@Operation(summary = "видалити користувача", description = "видаляє користувача за id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "користувача видалено"),
			@ApiResponse(responseCode = "404", description = "користувача не знайдено")
	})
	@DeleteMapping
	public ResponseEntity<?> deleteUser(Long id) {
		try {
			adminService.deleteUser(id);
			return ResponseEntity.ok("user deleted");
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

}
