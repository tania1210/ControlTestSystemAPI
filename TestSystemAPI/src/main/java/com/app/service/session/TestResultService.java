package com.app.service.session;

import com.app.controller.session.TestResultController;
import com.app.model.*;
import com.app.repository.StudentAnswerRepository;
import com.app.repository.StudentRepository;
import com.app.repository.TestRepository;
import com.app.repository.TestResultRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestResultService {

    private TestResultRepository testResultRepository;
    private StudentAnswerRepository studentAnswerRepository;
    private TestRepository testRepository;
    private StudentRepository studentRepository;

    public TestResultService(TestResultRepository testResultRepository, StudentAnswerRepository studentAnswerRepository,
                             TestRepository testRepository, StudentRepository studentRepository) {
        this.testResultRepository = testResultRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
    }

    public TestResult calculateResultTest(Long testId, Long studentId) throws EntityNotFoundException {
        // Перевіряємо, чи існує тест
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException("Test not found with id: " + testId));

        // Перевіряємо, чи існує студент
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        // Отримуємо відповіді студента для цього тесту
        List<StudentAnswer> studentAnswers = studentAnswerRepository.getByTestIdAndStudentId(testId, studentId);
        if (studentAnswers.isEmpty()) {
            throw new EntityNotFoundException("No answers found for student with id: " + studentId + " and test id: " + testId);
        }

        byte count = 0;
        for (StudentAnswer answer : studentAnswers) {
            if (answer.getAnswerId().getIsCorrect()) {
                count++;
            }
        }

        byte fullScore = test.getFullScore();
        double result = (double) (fullScore * count) / studentAnswers.size();

        return testResultRepository.save(new TestResult(result, test, student));
    }

}
