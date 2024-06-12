package com.app.security;

import com.app.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator implements CommandLineRunner {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void run(String... args) throws Exception {
        UserDetails adminDetails = userDetailsService.loadUserByUsername("t.sluzhynska@gmail.com");
        UserDetails userDetails = userDetailsService.loadUserByUsername("oleh@gmail.com");

        String adminToken = jwtTokenProvider.generateToken(adminDetails);
        String userToken = jwtTokenProvider.generateToken(userDetails);

        System.out.println("Admin Token: " + adminToken);
        System.out.println("User Token: " + userToken);
    }
}

