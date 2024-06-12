package com.app.service.authentication;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.dto.UserDTO;
import com.app.dto.UserDTOUpdate;
import com.app.model.User;
import com.app.repository.AdminRepository;
import com.app.security.Role;

@Service
public class AdminService {
	
	private AdminRepository adminRepository;
	
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
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
	
//main mathod for @PutMapping - undate datas
	public void updateUser(Long id, Map<String, Object> updateData) {
		User existingUser = userIsPresent(id).orElse(null);

		if(existingUser == null) {
			
			throw new IllegalArgumentException("user has not found");
		}else {
			updateData.forEach((key, value) -> {
		            switch (key) {
		                case "role":
		                	if(value instanceof Role) {
		                		existingUser.setRole((Role)value);
		                	}         
		                    break;
		            }
		        });
		}
	}
	
	public Optional<User> userIsPresent(Long id) {
		Optional<User> existingUser = adminRepository.findById(id);
		return existingUser;
	}
	
//main method of @deleteMapping - delete user
	public void deleteUser(Long id) throws IllegalArgumentException{
		User existingUser = userIsPresent(id).orElse(null);
		
		if(existingUser == null) {
			throw new IllegalArgumentException("user has not found");
		}else {
			adminRepository.delete(existingUser);
		}
	}
	
	
	
	
	
	
	
}
