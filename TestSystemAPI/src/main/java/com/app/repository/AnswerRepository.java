package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Answer;
import com.app.model.Question;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
	Answer getById(Long id);
	
	Answer save(Answer answer);
	
	void deleteById(Long id);
}
