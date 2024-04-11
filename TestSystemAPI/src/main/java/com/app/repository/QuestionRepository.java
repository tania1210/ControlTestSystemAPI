package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Question;
import com.app.model.Test;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	Question save(Question question);
    Question getById(Long id);
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "test_id")
    List<Question> findByTestId(Test test);
    
    void deleteById(Long id);
}
