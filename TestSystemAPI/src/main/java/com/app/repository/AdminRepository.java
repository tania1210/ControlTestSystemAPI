package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long>{

	List<User> findAll();
	
	User save(User user);
	void delete(User user);

}
