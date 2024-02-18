package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.app.service.UserService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    public SecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
//            .authorizeHttpRequests((authz) -> authz
//                .requestMatchers("/swagger-ui/**", 
//                		"/v3/api-docs/**", 
//                		"/user/id",
//                		"/v*/registration/**").permitAll()
//            );
        	.authorizeHttpRequests((authz) -> authz
        			.requestMatchers("/**").permitAll());

        return http.build();
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(daoAuthenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(bCryptPasswordEncoder);
    	provider.setUserDetailsService(userService);
    	return provider;
    }
    

	
}

