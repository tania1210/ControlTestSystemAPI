package com.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private JwtAuthenticationFilter jwtAuthFilter;

	private AuthenticationProvider authenticationProvider;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
		this.jwtAuthFilter = jwtAuthFilter;
		this.authenticationProvider = authenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable()) // Налаштування CSRF через Customizer
				.authorizeHttpRequests(auth -> auth // Використання authorizeHttpRequests для нових версій
//						.requestMatchers("/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
//						.requestMatchers("api/v1/login").permitAll()
						.requestMatchers("/**").permitAll()
//						.requestMatchers("api/v1/tests").hasAuthority("USER")

						.anyRequest().authenticated())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Використання Customizer для налаштування SessionManagement
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


		System.out.println("Security configuration loaded");
		return http.build();
	}

//
//	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//	}
//
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//				.csrf(AbstractHttpConfigurer::disable)
//				.sessionManagement(sessionManagement ->
//						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				)
//				.authorizeHttpRequests(authorizeRequests ->
//						authorizeRequests
//								.requestMatchers(
//										"/swagger-ui/**",
//										"/v3/api-docs",
//										"/v3/api-docs/**"
//								).permitAll()
//								.requestMatchers("/test/**").hasAuthority("ADMIN")
//								.requestMatchers("/getAllTypes", "/getAllTypes/").hasAuthority("ADMIN")
//								.requestMatchers("/swagger-ui/admin/**").hasAuthority("ADMIN")
//								.requestMatchers("/swagger-ui/user/**").hasAuthority("USER")
//								.anyRequest().authenticated()
//				)
//				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}
}

