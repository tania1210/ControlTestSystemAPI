package com.app.service.testing;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import com.app.model.*;
import com.app.repository.*;
import exceptions.TestAlreadyExistsException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TestService {

	private TestRepository testRepository;
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;
	private StudentRepository studentRepository;

	public TestService (TestRepository testRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, StudentRepository studentRepository ) {
		this.testRepository = testRepository;
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
		this.studentRepository = studentRepository;
	}
	
	public boolean isValidTimeFormat(String timeString) {
	    // Регулярний вираз для перевірки формату hh:mm:ss
	    String timePattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	    return timeString.matches(timePattern);
	}

	public Test createNewTest(String name, String duraction, byte attempts, byte fullScore) throws TestAlreadyExistsException, InvalidFormatException{
		Optional<Test> existingTest = testRepository.findByName(name);
		if(existingTest.isPresent()) {
			throw new TestAlreadyExistsException("test already exists with this name");
		}if(!isValidTimeFormat(duraction)) {
			throw new InvalidFormatException(null, duraction, fullScore, null);
		}else {
			Test test = testRepository.saveAndFlush(new Test(name, Time.valueOf(duraction), attempts, fullScore));		
			return test;
		}
	}

	public void setTestDatas(Long id, String name, String duraction, byte fullScore, byte attempts) throws InvalidFormatException, EntityNotFoundException{
		Optional<Test> existingTest = testRepository.findById(id);
		if(!existingTest.isPresent()) {
			throw new EntityNotFoundException("test not found. No data's been changed");
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
			}
			testRepository.save(test);
		}
	}

	public void deleteTest(Long id) throws EntityNotFoundException{
		Optional<Test> existingTest = testRepository.findById(id);
		if(!existingTest.isPresent()) {
			throw new EntityNotFoundException("Test not found. Wrong id");
		}else {
			List<Question> questions = questionRepository.findByTestId(existingTest.get());

			for(Question question: questions) {

				List<Answer> answers = answerRepository.findByQuestionId(question);
				for(Answer answer: answers) {
					answerRepository.deleteById(answer.getId());
				}
				questionRepository.deleteById(question.getId());
			}
			testRepository.deleteById(id);
		}

	}

	public List<Test> fetchTest(Long id) throws EntityNotFoundException {
		Optional<Student> existingStudent = studentRepository.findStudentByUserId(id);
		if(!existingStudent.isPresent()) {
			throw new EntityNotFoundException("Student not found. Wrong id");
		}else {
			List<Test> tests = testRepository.findAllTestsByStudentId(existingStudent.get().getId());
			if(tests.isEmpty()) {
				throw new EntityNotFoundException("no test's been found");
			}
			return tests;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
	
}
