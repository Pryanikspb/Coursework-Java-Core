package pro.sky.courseworkJavaCore.Service.impl;

import org.springframework.stereotype.Service;
import pro.sky.courseworkJavaCore.Service.QuestionService;
import pro.sky.courseworkJavaCore.exceptions.QuestionAlreadyExistsException;
import pro.sky.courseworkJavaCore.exceptions.QuestionNotFoundException;
import pro.sky.courseworkJavaCore.exceptions.QuestionsAreEmptyException;
import pro.sky.courseworkJavaCore.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if(!questions.add(question)) {
            throw new QuestionAlreadyExistsException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if(questions.isEmpty()){
            throw new QuestionsAreEmptyException();
        }
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}
