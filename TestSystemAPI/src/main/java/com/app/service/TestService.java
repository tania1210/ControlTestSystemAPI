package com.app.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.TestDTO;
import com.app.dto.UserDTO;
import com.app.dto.QuestionDTO;
import com.app.model.Answer;
import com.app.model.Question;
import com.app.model.Test;
import com.app.model.TypeOfQuestion;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestionRepository;
import com.app.repository.TestRepository;
import com.app.repository.TypeOfQuestionRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TestService {

	private TestRepository testRepository;
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;
	private TypeOfQuestionRepository typeOfQuestionRepository;
	
	public TestService (TestRepository testRepository, TypeOfQuestionRepository typeOfQuestionRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
		this.testRepository = testRepository;
		this.questionRepository = questionRepository;
		this.typeOfQuestionRepository = typeOfQuestionRepository;
		this.answerRepository = answerRepository;
	}


	
	public boolean isValidTimeFormat(String timeString) {
	    // Регулярний вираз для перевірки формату hh:mm:ss
	    String timePattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	    return timeString.matches(timePattern);
	}
	
//CREATE
//TEST SERVICE

	public Long createNewTest(String name, String duraction, byte attempts, byte fullScore) throws InvalidFormatException{
		if(!isValidTimeFormat(duraction)) {
			throw new InvalidFormatException(null, duraction, fullScore, null);
		}else {
			Test test = testRepository.saveAndFlush(new Test(name, Time.valueOf(duraction), attempts, fullScore));		
			return test.getId();
		}
	}
	
//QUESTION SERVICE
	public Long createNewQuestion(String text, String typeN, Long testId) throws IllegalArgumentException, EntityNotFoundException{
		TypeOfQuestion type = typeOfQuestionRepository.findByName(typeN);
		if(text.isEmpty() || type == null) {
			throw new IllegalArgumentException();
		}else if(testRepository.getById(testId) == null){
			throw new EntityNotFoundException();
		}else {
			System.out.println("class of question is = " + questionRepository.getById((long) 8).getClass());
			Question question = questionRepository.save(new Question(text, type, testRepository.getById(testId)));//test.orElseThrow(() -> new IllegalArgumentException()
			return question.getId();
		}
 
	}
	
//ANSWER SERVICE
	
	public Long createNewAnswer(String text, boolean isCorrect, Long questionId) throws EntityNotFoundException{
			
		if(questionRepository.getById(questionId) == null) {
			throw new EntityNotFoundException();
		}else {
			Answer answer = answerRepository.save(new Answer(text, isCorrect, questionRepository.getById(questionId)));
			return answer.getId();
		}
		
	}
	

//SET
//TEST SERVICE	
	
	public void setTestDatas(Long id, String name, String duraction, byte fullScore, byte attempts) throws InvalidFormatException, NullPointerException{
		if(id == null) {
			throw new NullPointerException();
		}else {
			Test test = testRepository.getById(id);
			if(name != null) {
				test.setName(name);
			}if (duraction != null) {
				if(!isValidTimeFormat(duraction)) {
					throw new InvalidFormatException(null, duraction, fullScore, null);
				}else {
					test.setDuraction(Time.valueOf(duraction));
				}			
			}if(attempts != 0) {
				test.setAttemps(attempts);
			}else {
				
			}
			testRepository.save(test);
		}
		
	}
	
	public void setQuestionDatas(Long id, String questionText, String typeS) throws EntityNotFoundException, NullPointerException{
		if(id == null) {
			throw new NullPointerException();
		}else {
			Question question = questionRepository.getById(id);
			if(questionText != null) {
				question.setQuestionText(questionText);
			}if(typeS != null) {
				TypeOfQuestion type = typeOfQuestionRepository.findByName(typeS);
				question.setType(type);
			}
			questionRepository.save(question);
		}
		
	}
	
	public void setAnswerDatas(Long id, String answerText, boolean isCorrect) throws NullPointerException{
		if(id == null) {
			throw new NullPointerException();
		}else {
			Answer answer = answerRepository.getById(id);
			if(answerText != null) {
				answer.setAnswerText(answerText);
			}if(isCorrect != answer.getIsCorrect()) {
				answer.setIsCorrect(isCorrect);
			}
			answerRepository.save(answer);
		}
	}
		
//DELETE
	public void deleteTest(Long id) throws EntityNotFoundException{
		Test test = testRepository.getById(id);
		
			for(int i = 0; ; i++) {
				List<Question> question = questionRepository.findByTestId(test);
				System.out.println("question found");
				questionRepository.deleteById(question.get()));
			    if (question.isEmpty()) {
			        break; // Виходимо з циклу, якщо список порожній
			    }
			}
//		}catch() {
//			
//		}

//		Question question = 
		
//		testRepository.deleteById(id);
//		return question;
	}
	
	public void deleteQuestion(Long id) throws EntityNotFoundException{
		questionRepository.deleteById(id);
	}
	
	public void deleteAnswer(Long id) throws EntityNotFoundException{
		answerRepository.deleteById(id);
	}
	
//SHOW
	public List<String> showAllNamesOfType() {
		List<TypeOfQuestion> typeObj = typeOfQuestionRepository.findAll();
		return typeObj.stream().map(TypeOfQuestion::getName).collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
	
}
