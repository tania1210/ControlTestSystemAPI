package com.app.service.session;

import com.app.controller.session.TestResultController;
import com.app.model.Answer;
import com.app.model.StudentAnswer;
import com.app.model.Test;
import com.app.model.TestResult;
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
        List<StudentAnswer> studentAnswers = studentAnswerRepository.getByTestIdAndStudentId(testId, studentId);
        if(studentAnswers.isEmpty()) {
            throw new EntityNotFoundException("");
        }else {
            byte count = 0;
            for(StudentAnswer answer: studentAnswers) {
                if(answer.getAnswerId().getIsCorrect()) {
                    count ++;
                }
            }
            byte fullScore = testRepository.findById(testId).get().getFullScore();
            double result = fullScore * (count * 100 / 25) / 100;
            return testResultRepository.save(new TestResult(result, testRepository.findById(testId).get(), studentRepository.getById(studentId)));
        }
    }
}
