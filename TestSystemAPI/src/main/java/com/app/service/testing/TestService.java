package com.app.service.testing;

import java.sql.Time;
import java.util.List;

import com.app.model.*;
import com.app.repository.*;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TestService {

	private TestRepository testRepository;
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;
//	private GroupRepository groupRepository;
//	private SubjectRepository subjectRepository;
	private StudentRepository studentRepository;

	public TestService (TestRepository testRepository, QuestionRepository questionRepository, AnswerRepository answerRepository,
						GroupRepository groupRepository, SubjectRepository subjectRepository, StudentRepository studentRepository
	) {
		this.testRepository = testRepository;
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
//		this.groupRepository = groupRepository;
//		this.subjectRepository = subjectRepository;
		this.studentRepository = studentRepository;
	}
	
	public boolean isValidTimeFormat(String timeString) {
	    // Регулярний вираз для перевірки формату hh:mm:ss
	    String timePattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	    return timeString.matches(timePattern);
	}

	public Long createNewTest(String name, String duraction, byte attempts, byte fullScore) throws InvalidFormatException{
		if(!isValidTimeFormat(duraction)) {
			throw new InvalidFormatException(null, duraction, fullScore, null);
		}else {
			Test test = testRepository.saveAndFlush(new Test(name, Time.valueOf(duraction), attempts, fullScore));		
			return test.getId();
		}
	}

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

	public void deleteTest(Long id) throws EntityNotFoundException{
		Test test = testRepository.getById(id);
		List<Question> questions = questionRepository.findByTestId(test);

		for(Question question: questions) {

			List<Answer> answers = answerRepository.findByQuestionId(question);
			for(Answer answer: answers) {
				answerRepository.deleteById(answer.getId());
			}
			questionRepository.deleteById(question.getId());
		}
		testRepository.deleteById(id);
	}

	public List<Test> fetchTest(Long id) {
		Student student = studentRepository.findStudentByUserId(id);

		return testRepository.findAllTestsByStudentId(student.getId());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
	
}
