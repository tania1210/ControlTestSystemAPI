package com.app.service.authentication;

import com.app.auth.AuthenticationRequest;
import com.app.auth.AuthenticationResponse;
import com.app.auth.RegistrationRequest;
import com.app.config.JwtService;
import com.app.security.Role;
import exceptions.EmailNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.model.User;
import com.app.repository.UserRepository;
import exceptions.IncorrectPasswordException;


@Service
public class AuthorizationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthorizationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

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
		return new AuthenticationResponse(jwtToken);
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) throws EmailNotFoundException, IncorrectPasswordException {
		System.out.println("Authenticating user with email: " + request.getEmail());
// Отримання користувача з бази даних
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new EmailNotFoundException("Email not found: " + request.getEmail()));

// Перевірка пароля
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new IncorrectPasswordException("Incorrect password for user: " + request.getEmail());
		}

		System.out.println("Attempting authentication...");
		var authResult = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
				)
		);
		System.out.println("Authentication successful for user with email: " + request.getEmail());

		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}

}
