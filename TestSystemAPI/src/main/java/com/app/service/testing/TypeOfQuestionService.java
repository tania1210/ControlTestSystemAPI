package com.app.service.testing;

import com.app.model.TypeOfQuestion;
import com.app.repository.TypeOfQuestionRepository;
import exceptions.QuestionTypeAlreadyExistsException;
import exceptions.TestAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOfQuestionService {

    private TypeOfQuestionRepository typeOfQuestionRepository;

    public TypeOfQuestionService(TypeOfQuestionRepository typeOfQuestionRepository) {
        this.typeOfQuestionRepository = typeOfQuestionRepository;
    }

    public TypeOfQuestion addTypeOfQuestion(String name) throws QuestionTypeAlreadyExistsException {
        if(typeOfQuestionRepository.findByName(name).isPresent()) {
            throw new QuestionTypeAlreadyExistsException("this question type is already exists");
        }else {
            return typeOfQuestionRepository.save(new TypeOfQuestion(name));
        }

    }

    public TypeOfQuestion setTypeOfQuestion(Long id, String name) throws EntityNotFoundException {
        if(typeOfQuestionRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("question type not found. Wrong id");
        }else {
            TypeOfQuestion typeOfQuestion = typeOfQuestionRepository.getById(id);
            typeOfQuestion.setName(name);
            return typeOfQuestionRepository.save(typeOfQuestion);
        }
    }

    public void deleteTypeOfQuestion(Long id) throws EntityNotFoundException {
        if(typeOfQuestionRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("question type not found. Wrong id");
        }else {
            typeOfQuestionRepository.deleteById(id);
        }

    }

    public List<TypeOfQuestion> getAllNamesOfType() {
        List<TypeOfQuestion> typeObj = typeOfQuestionRepository.findAll();
        return typeObj;
    }
}
