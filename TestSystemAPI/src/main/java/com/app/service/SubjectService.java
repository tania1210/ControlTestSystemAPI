package com.app.service;

import com.app.model.*;
import com.app.repository.SubjectRepository;
import com.app.repository.UserRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import exceptions.SubjectAlreadyExistsException;
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

    public Subject createNewSubject(String name, Long id) throws EntityNotFoundException, SubjectAlreadyExistsException {
        if(subjectRepository.findByName(name).isPresent()) {
            throw new SubjectAlreadyExistsException("subject with this name is already exists");
        }else {
            if(userRepository.findById(id).isEmpty()) {
                throw new EntityNotFoundException("user's not found. Wrong id");
            }else {
                Optional<User> user = userRepository.findById(id);
                return subjectRepository.save(new Subject(name, user.get()));
            }
        }
    }

    public Subject setSubject(Long id, String name, Long userId) throws EntityNotFoundException {
        if(subjectRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("subject's not found. Wrong id");
        }else {
            if(userRepository.findById(userId).isEmpty()) {
                throw new EntityNotFoundException("user not found. Wrong id");
            } else {
                Subject subject = subjectRepository.getById(id);
                if(!name.equals(subject.getName())) {
                    subject.setName(name);
                }
                return subjectRepository.save(subject);
            }
        }
    }

    public void deleteSubject(Long id) throws EntityNotFoundException {
        if(subjectRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("subject's not found with this id");
        }else {
            subjectRepository.deleteById(id);
        }
    }

    public List<Subject> fetchSubject(Long id) throws EntityNotFoundException {
        if(userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("user's not found with this id");
        }else {
            return subjectRepository.findAllSubjectByUserId(id);
        }

    }


}
