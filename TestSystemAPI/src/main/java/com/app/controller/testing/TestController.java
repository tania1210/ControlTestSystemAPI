package com.app.controller.testing;

import com.app.model.Test;
import exceptions.TestAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@PreAuthorize("hasRole('USER')")
//@PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
public class TestController {
	
		private TestService testService;
		
		public TestController(TestService testService) {
			this.testService = testService;
		}


		@Operation(summary = "створити новий тест", description = "повертає створений обʼєкт тесту")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "тест успішно створено"),
				@ApiResponse(responseCode = "409", description = "тест із такою назвою уже існує"),
				@ApiResponse(responseCode = "422", description = "некоректний тип тривалості тесту"),
		})
		@PostMapping
//		@PreAuthorize("hasAuthority('ADMIN')")
		public ResponseEntity<?> createNewTest(@Parameter(description = "Name of the test") @RequestParam String name,
											   @Parameter(description = "Duration of the test") @RequestParam String duration,
											   @Parameter(description = "Full score of the test") @RequestParam byte fullScore,
											   @Parameter(description = "Number of attempts allowed") @RequestParam byte attempts) {
			try {
				return ResponseEntity.status(HttpStatus.CREATED).body(testService.createNewTest(name, duration, fullScore, attempts));
			}catch(TestAlreadyExistsException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}catch(InvalidFormatException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid format for duration");
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
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Invalid format for duration");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}

		@DeleteMapping
		@PreAuthorize("hasAuthority('ADMIN')")
		public ResponseEntity<?> deleteTest(@Parameter(description = "id of the test") @RequestParam Long id) {
			try {
				testService.deleteTest(id);
				return ResponseEntity.ok("test was deleted");
			}catch(EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}

		@GetMapping
		@PreAuthorize("hasRole('USER')")
		public ResponseEntity<?> fetchTest(@Parameter(description = "id of the user") @RequestParam Long id) {

			try {
				List<Test> tests = testService.fetchTest(id);
				return ResponseEntity.ok(tests);
			}catch (EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}

		}
		
//SHOW TEST DATAS
		

		
		
}



