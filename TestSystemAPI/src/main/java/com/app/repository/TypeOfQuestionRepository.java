package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Test;
import com.app.model.TypeOfQuestion;

@Repository
public interface TypeOfQuestionRepository extends JpaRepository<TypeOfQuestion, Long>{

	TypeOfQuestion getById(Long id);
	TypeOfQuestion findByName(String name);

	void deleteById(Long id);

//	List<TypeOfQuestion> findAll();
}
