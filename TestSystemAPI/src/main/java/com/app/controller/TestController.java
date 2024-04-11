package com.app.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.QuestionDTO;
import com.app.model.Question;
import com.app.model.Test;
import com.app.model.TypeOfQuestion;
import com.app.service.TestService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/test")
//@PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
public class TestController {
	
		private TestService testService;
		
		public TestController(TestService testService) {
			this.testService = testService;
		}
		
//CREATE METHODS
		
		@PostMapping("/createNewTest")
		public ResponseEntity<?> createNewTest(String name, String duraction, byte fullScore, byte attempts) {
			try {
				Long id = testService.createNewTest(name, duraction, fullScore, attempts);
				return ResponseEntity.ok(id);

			}catch(InvalidFormatException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Type of time is not correct");
			}
		}

		@PostMapping("/createNewQuestion")
	    public ResponseEntity<?> createNewQuestion(String text, String type, Long testId) {
			try {
				Long id = testService.createNewQuestion(text, type, testId);
				return ResponseEntity.ok(id);
			}catch(IllegalArgumentException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datas was incorrect");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("test not found");
			}
	    }

		@PostMapping("/createNewAnswer")
		public ResponseEntity<?> addAnswerSession(String text, boolean isCorrect, Long questionId) {
			try {
				Long id = testService.createNewAnswer(text, isCorrect, questionId);
				return ResponseEntity.ok(id);
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("question not found");
			}
		}
		
//SET METHODS
		@PutMapping("/setTestDatas")
		public ResponseEntity<String> setTest(Long id, String name, String duraction, byte fullScore, byte attempts) {
			try {
				testService.setTestDatas(id, name, duraction, fullScore, attempts);
				return ResponseEntity.ok("Test datas was update");
			}catch(InvalidFormatException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid format of time");
			}catch(NullPointerException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("any data was change. Test wasn't found");
			}
		}
		
		@PutMapping("/setQuestionDatas")
		public ResponseEntity<String> setQuestion(Long id, String questionText, String type) {
			try {
				testService.setQuestionDatas(id, questionText, type);
				return ResponseEntity.ok("Question datas was update");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found this type of question");
			}catch(NullPointerException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("any data was change. Question wasn't found");
			}
		}
		
		@PutMapping("/setAnswerDatas")
		public ResponseEntity<String> setAnswer(Long id, String answerText, boolean isCorrect) {
			try {
				testService.setAnswerDatas(id, answerText, isCorrect);
				return ResponseEntity.ok("Answer datas was update");
			}catch(NullPointerException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("any data was change. Answer wasn't found");
			}
		}
		
		
//DELETE METHODS
		
		@DeleteMapping("/deleteTest")
		public ResponseEntity<?> deleteTest(Long id) {
			try {
				testService.deleteTest(id);
				return ResponseEntity.ok("");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test wasn`t found. Wrong id");
			}
		}
		
		@DeleteMapping("/deleteQuestion")
		public ResponseEntity<String> deleteQuestion(Long id) {
			try {
				testService.deleteQuestion(id);
				return ResponseEntity.ok("Question was delete");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question wasn`t found. Wrong id");
			}
		}
		
		@DeleteMapping("/deleteAnswer")
		public ResponseEntity<String> deleteAnswer(Long id) {
			try {
				testService.deleteAnswer(id);
				return ResponseEntity.ok("Answer was delete");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Answer wasn`t found. Wrong id");
			}
		}
		
//SHOW TEST DATAS
		
		@GetMapping("/getAllTypes")
		public List<String> getAllTypesOfQuestion() {
			return testService.showAllNamesOfType();
		}
		
		
}



