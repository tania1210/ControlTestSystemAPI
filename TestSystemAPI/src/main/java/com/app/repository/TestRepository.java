package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Question;
import com.app.model.Test;
import com.app.model.TypeOfQuestion;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>{

    Test save(Test test);
     
    
    Question save(Question question);

}
