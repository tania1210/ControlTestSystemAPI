package com.app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.User;

@Repository
public interface RegistrationRepository extends JpaRepository<User, Long>{
		User save(User user);

}
