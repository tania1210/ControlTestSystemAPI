package com.app.service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.TestDTO;
import com.app.dto.UserDTO;
import com.app.model.Question;
import com.app.model.Test;
import com.app.model.TypeOfQuestion;
import com.app.repository.TestRepository;
import com.app.repository.TypeOfQuestionRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@Service
public class TestService {

	private TestRepository testRepository;
	private TypeOfQuestionRepository typeOfQuestionRepository;
	private Test test;
	
	public TestService (TestRepository testRepository, TypeOfQuestionRepository typeOfQuestionRepository) {
		this.testRepository = testRepository;
		this.typeOfQuestionRepository = typeOfQuestionRepository;
	}

//Test's service
	
	public boolean isValidTimeFormat(String timeString) {
	    // Регулярний вираз для перевірки формату hh:mm:ss
	    String timePattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	    return timeString.matches(timePattern);
	}
	
	public ResponseEntity<String> addTest(String name, String duraction, byte fullScore, byte attempts) throws InvalidFormatException {
		if(!isValidTimeFormat(duraction)) {
			throw new InvalidFormatException("incorrect type of time ", duraction, null);
		}else {
			test = new Test(name, Time.valueOf(duraction), fullScore, attempts);
			testRepository.save(test);
			return ResponseEntity.ok("Test was created successfully");
		}
		
	}
	
//Question's service
	public ResponseEntity<String> addQuestion(Long id, String text, String typeName, byte score) {
		Optional<TypeOfQuestion> type = typeOfQuestionRepository.findByName(typeName);
		
		Question question = new Question(text, score, type.orElse(null), test);
		testRepository.save(question);
		
		return ResponseEntity.ok("question added");
	}
		
//TypeOfQuestion's service
	public List<String> getAllNames() {
		List<TypeOfQuestion> typeObj = typeOfQuestionRepository.findAll();
		System.out.println("=============================" + typeObj.get(0));
		return typeObj.stream().map(TypeOfQuestion::getName).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
	
}
