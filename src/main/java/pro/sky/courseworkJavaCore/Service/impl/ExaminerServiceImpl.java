package pro.sky.courseworkJavaCore.Service.impl;

import org.springframework.stereotype.Service;
import pro.sky.courseworkJavaCore.Service.ExaminerService;
import pro.sky.courseworkJavaCore.Service.QuestionService;
import pro.sky.courseworkJavaCore.exceptions.IncorrectAmountOfQuestions;
import pro.sky.courseworkJavaCore.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if(amount <=0 || amount > questionService.getAll().size()){
            throw new IncorrectAmountOfQuestions();
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
