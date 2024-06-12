package com.app.service.testing;

import com.app.model.TypeOfQuestion;
import com.app.repository.TypeOfQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOfQuestionService {

    private TypeOfQuestionRepository typeOfQuestionRepository;

    public TypeOfQuestionService(TypeOfQuestionRepository typeOfQuestionRepository) {
        this.typeOfQuestionRepository = typeOfQuestionRepository;
    }

    public void addTypeOfQuestion(String name) {
        typeOfQuestionRepository.save(new TypeOfQuestion(name));
    }

    //SET
    public void setTypeOfQuestion(Long id, String name) {
        TypeOfQuestion typeOfQuestion = typeOfQuestionRepository.getById(id);
        typeOfQuestion.setName(name);
    }

    //DELETE
    public void deleteTypeOfQuestion(Long id) {
        typeOfQuestionRepository.deleteById(id);
    }

    //SHOW
    public List<String> showAllNamesOfType() {
        List<TypeOfQuestion> typeObj = typeOfQuestionRepository.findAll();
        return typeObj.stream().map(TypeOfQuestion::getName).collect(Collectors.toList());
    }
}
