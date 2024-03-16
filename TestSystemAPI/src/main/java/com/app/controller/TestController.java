package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.TestService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestController
@RequestMapping("/tests")
@PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
public class TestController {
	
		private TestService testService;
		
		public TestController(TestService testService) {
			this.testService = testService;
		}
		
//Test's controllers
		
		@PostMapping("/CreateTest")
		public ResponseEntity<String> createTest(String name, String duraction, byte fullScore, byte attemps) {
			ResponseEntity<String> message = null;
			try {
				message = testService.addTest(name, duraction, fullScore, attemps);
			}catch(InvalidFormatException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Type of time is not correct");
			}
			return message;
		}
		
//question's controllers
		
		@PostMapping("/{testId}/CreateQuestion")
		public ResponseEntity<String> createQuestion(Long id, String text, String type, byte score) {
			ResponseEntity<String> message = null;
			
			message = testService.addQuestion(id, text, type, score);
			
			return message;
		}
		
		@GetMapping("/types")
		public List<String> getAllTypesOfQuestion() {
			List<String> list = testService.getAllNames();
			System.out.println("list = " + list);
			return list;
		}
		
		
}
