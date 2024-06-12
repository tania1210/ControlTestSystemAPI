package com.app.service.session;

import com.app.controller.session.TestResultController;
import com.app.repository.TestResultRepository;
import org.springframework.stereotype.Service;

@Service
public class TestResultService {

    private TestResultRepository testResultRepository;

    public TestResultService(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }



}
