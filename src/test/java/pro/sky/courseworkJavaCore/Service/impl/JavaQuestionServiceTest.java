package pro.sky.courseworkJavaCore.Service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.courseworkJavaCore.Service.QuestionService;
import pro.sky.courseworkJavaCore.exceptions.QuestionAlreadyExistsException;
import pro.sky.courseworkJavaCore.exceptions.QuestionNotFoundException;
import pro.sky.courseworkJavaCore.exceptions.QuestionsAreEmptyException;
import pro.sky.courseworkJavaCore.model.Question;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach(){
        questionService.add(new Question("Q1", "A1"));
        questionService.add(new Question("Q2", "A2"));
        questionService.add(new Question("Q3", "A3"));
    }

    @AfterEach
    public void afterEach() {
        new HashSet<>(questionService.getAll()).forEach(question -> questionService.remove(question));
    }
    @Test
    public void addTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q4", "A4");

        assertThat(questionService.add(question))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);

    }

    @Test
    public void addTest2() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q4", "A4");

        assertThat(questionService.add("Q4", "A4"))
                .isEqualTo(question)
                .isIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount + 1);

    }

    @Test
    public void addNegativeTest() {
        Question question = new Question("Q1", "A1");

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(question));

    }

    @Test
    public void addNegativeTest2() {
        Question question = new Question("Q1", "A1");

        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add("Q1", "A1"));
    }

    @Test
    public void removeTest() {
        int beforeCount = questionService.getAll().size();
        Question question = new Question("Q2", "A2");

        assertThat(questionService.remove(question))
                .isEqualTo(question)
                .isNotIn(questionService.getAll());
        assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Q4", "A4")));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll())
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q2", "A2"),
                        new Question("Q3", "A3")
                )
                .hasSize(3);
    }

    @Test
    public void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        afterEach();

        assertThatExceptionOfType(QuestionsAreEmptyException.class)
                .isThrownBy(questionService :: getRandomQuestion);
    }

}
