package pro.sky.courseworkJavaCore.Service;

import pro.sky.courseworkJavaCore.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
