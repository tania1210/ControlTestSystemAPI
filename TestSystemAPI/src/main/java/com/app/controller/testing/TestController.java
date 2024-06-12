package com.app.controller.testing;

import com.app.model.Test;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.app.service.testing.TestService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("api/v1/tests")
//@PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
public class TestController {
	
		private TestService testService;
		
		public TestController(TestService testService) {
			this.testService = testService;
		}

		@PostMapping
		@PreAuthorize("hasAuthority('ADMIN')")
		public ResponseEntity<?> createNewTest(@Parameter(description = "Name of the test") @RequestParam String name,
											   @Parameter(description = "Duration of the test") @RequestParam String duration,
											   @Parameter(description = "Full score of the test") @RequestParam byte fullScore,
											   @Parameter(description = "Number of attempts allowed") @RequestParam byte attempts) {
			try {
				Long id = testService.createNewTest(name, duration, fullScore, attempts);
				return ResponseEntity.ok(id);

			}catch(InvalidFormatException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Type of time is not correct");
			}
		}

		@PatchMapping
		public ResponseEntity<String> setTest(@Parameter(description = "id of the test") @RequestParam Long id,
											  @Parameter(description = "Name of the test") @RequestParam String name,
											  @Parameter(description = "duraction of the test") @RequestParam String duraction,
											  @Parameter(description = "fullScore of the test") @RequestParam byte fullScore,
											  @Parameter(description = "attempts of the test") @RequestParam byte attempts) {
			try {
				testService.setTestDatas(id, name, duraction, fullScore, attempts);
				return ResponseEntity.ok("Test datas was update");
			}catch(InvalidFormatException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid format of time");
			}catch(NullPointerException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("any data was change. Test wasn't found");
			}
		}

		@DeleteMapping
		public ResponseEntity<?> deleteTest(@Parameter(description = "id of the test") @RequestParam Long id) {
			try {
				testService.deleteTest(id);
				return ResponseEntity.ok("");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test wasn`t found. Wrong id");
			}
		}

		@GetMapping
		public List<Test> fetchTest(@Parameter(description = "id of the user") @RequestParam Long id) {

			return testService.fetchTest(id);
		}
		
//SHOW TEST DATAS
		

		
		
}



