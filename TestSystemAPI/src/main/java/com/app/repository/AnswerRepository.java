package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Answer;
import com.app.model.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
	Answer getById(Long id);
	Optional<Answer> findByAnswerText(String text);
	Answer save(Answer answer);
	
	void deleteById(Long id);

	List<Answer> findByQuestionId(Question question);

	@Query("SELECT a FROM Answer a WHERE a.questionId.id = :questionId")
	List<Answer> findAllByQuestionId(@Param("questionId") Long questionId);


}
