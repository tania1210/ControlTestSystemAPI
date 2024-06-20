package com.app.service.authentication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.dto.UserDTOUpdate;
import com.app.model.User;
import com.app.repository.AdminRepository;
import com.app.security.Role;

@Service
public class AdminService {
	
	private AdminRepository adminRepository;
	private UserRepository userRepository;
	
	public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
		this.adminRepository = adminRepository;
		this.userRepository = userRepository;
	}
	
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());

        return userDTO;
    }
    
    private UserDTOUpdate convertToDTOUpdate(User user) {
    	UserDTOUpdate userDTOUpdate = new UserDTOUpdate();
    	userDTOUpdate.setRole(user.getRole());

        return userDTOUpdate;
    }
	
	public List<UserDTO> getAllUsers() {
		 List<User> users = adminRepository.findAll();
		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public void updateUserRole(Long id, String role) throws IllegalArgumentException, EntityNotFoundException{
		Optional<User> user = userRepository.findById(id);

		if(user.isEmpty()) {
			throw new EntityNotFoundException("user hasn't found");
		}else {
			boolean isValidRole = Arrays.stream(Role.values())
					.anyMatch(r -> r.name().equalsIgnoreCase(role));

			if (!isValidRole) {
				throw new IllegalArgumentException("Invalid role: " + role);
			}
			Role newRole = Role.valueOf(role.toUpperCase());
			user.get().setRole(newRole);
			userRepository.save(user.get());
		}
	}
	
	public Optional<User> userIsPresent(Long id) {
		Optional<User> existingUser = adminRepository.findById(id);
		return existingUser;
	}
	
//main method of @deleteMapping - delete user
	public void deleteUser(Long id) throws EntityNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new EntityNotFoundException("user hasn't found");
		}else {
			userRepository.delete(user.get());
		}
	}
	
	
	
	
	
	
	
}
