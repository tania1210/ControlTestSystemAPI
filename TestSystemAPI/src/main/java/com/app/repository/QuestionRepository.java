package com.app.repository;

import java.util.List;
import java.util.Optional;

import com.app.model.Subject;
import com.app.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	Question save(Question question);
    Question getById(Long id);
    Optional<Question> findByQuestionText(String questionText);
    Optional<Question> findById(Long id);

    List<Question> findByTestId(Test test);

    void deleteById(Long id);

    @Query("SELECT q FROM Question q WHERE q.testId.id = :testId")
    List<Question> findAllQuestionByTestId(@Param("testId") Long testId);
}
