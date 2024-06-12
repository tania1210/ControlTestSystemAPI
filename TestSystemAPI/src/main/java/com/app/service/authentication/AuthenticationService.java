package com.app.service.authentication;

import com.app.auth.AuthenticationRequest;
import com.app.auth.AuthenticationResponse;
import com.app.auth.RegistrationRequest;
import com.app.config.JwtService;
import com.app.security.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class AuthenticationService {

	private  UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	private JwtService jwtService;

	private AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegistrationRequest request) {
		var user = new User.Builder()
				.firstname(request.getFirstName())
				.lastname(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		userRepository.save(user);

		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse.Builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				)
		);
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
		return null;
	}


//	private UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	public AuthenticationService(UserRepository userRepository) {
//		this.passwordEncoder = new PasswordEncoder();
//		this.userRepository = userRepository;
//	}
//
//	public ResponseEntity<String> login(String email, String password) throws UserNotFoundException, IncorrectPasswordException{
//		Optional<User> registeredUser = userRepository.findByEmail(email);
//		if(!registeredUser.isPresent()) {
//			throw new UserNotFoundException();
//		}
//
//		String haschedPasswordFromBD = registeredUser.get().getPassword();
//		if(passwordEncoder.bCryptPasswordEncoder().matches(password, haschedPasswordFromBD)) {
//				return ResponseEntity.ok("user is authenticated");
//			}else {
//				throw new IncorrectPasswordException();
//		}
//
//	}
	
	
}
