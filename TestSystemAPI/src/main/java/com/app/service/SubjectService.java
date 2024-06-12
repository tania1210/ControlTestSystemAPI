package com.app.service;

import com.app.model.*;
import com.app.repository.SubjectRepository;
import com.app.repository.UserRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public void createNewSubject(String name, String duraction, Long id) {
        Optional<User> user = userRepository.findById(id);
        subjectRepository.save(new Subject(name, user.get()));

    }

    public void setSubject(Long id, String name) {
        if(id == null) {
            throw new NullPointerException();
        }else {
            Subject subject = subjectRepository.getById(id);
            if(name != null) {
                subject.setName(name);
            }
            subjectRepository.save(subject);
        }
    }

    public void deleteTest(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> fetchSubject(Long id) {
        return subjectRepository.findAllSubjectByUserId(id);
    }


}
