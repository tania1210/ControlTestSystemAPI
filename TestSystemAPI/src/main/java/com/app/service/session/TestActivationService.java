package com.app.service.session;

import com.app.model.Test;
import com.app.model.TestActivation;
import com.app.repository.TestActivationRepository;
import com.app.repository.TestRepository;
import exceptions.TestDeactivatedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TestActivationService {

    private TestActivationRepository testActivationRepository;
    private TestRepository testRepository;

    public TestActivationService(TestActivationRepository testActivationRepository, TestRepository testRepository) throws EntityNotFoundException{
        this.testActivationRepository = testActivationRepository;
        this.testRepository = testRepository;
    }

    public void activateTest(LocalDateTime endTime, Long testId) {
        Optional<Test> test = testRepository.findById(testId);
        if(test.isEmpty()) {
            throw new EntityNotFoundException(String.format("test has not found with id :%testId", testId));
        }else {
            test.get().setActive(true);
            testRepository.save(test.get());
            testActivationRepository.save(new TestActivation(LocalDateTime.now(), endTime, test.get()));
        }
    }

    public void deactivateTest(Long testId) throws EntityNotFoundException, TestDeactivatedException{
        Optional<Test> test = testRepository.findById(testId);
        if(test.isEmpty()) {
            throw new EntityNotFoundException(String.format("test has not found with id :%testId", testId));
        }else {
            if(!test.get().getActivate()) {
                throw new TestDeactivatedException("test's already deactivate");
            }else {
                TestActivation testActivation = testActivationRepository.getByTestId(testId);
                if(testActivation.getEndTime().isAfter(LocalDateTime.now())) {
                    throw new TestDeactivatedException("test cannot be deactivate. It has some time");
                }else {
                    test.get().setActive(false);
                    testRepository.save(test.get());
                    testActivationRepository.deleteById(testId);
                }
            }

        }
    }


}
