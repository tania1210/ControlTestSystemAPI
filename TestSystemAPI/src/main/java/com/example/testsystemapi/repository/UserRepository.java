package com.example.testsystemapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testsystemapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
