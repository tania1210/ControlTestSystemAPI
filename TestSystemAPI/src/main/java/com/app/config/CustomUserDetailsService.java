//package com.app.config;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private Map<String, UserDetails> users = new HashMap<>();
//
//    public CustomUserDetailsService() {
//        users.put("admin", User.withUsername("admin")
//                .password("{noop}password")
//                .roles("ADMIN")
//                .build());
//        users.put("user", User.withUsername("user")
//                .password("{noop}password")
//                .roles("USER")
//                .build());
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (!users.containsKey(username)) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return users.get(username);
//    }
//}
//
