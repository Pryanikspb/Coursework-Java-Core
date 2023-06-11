package pro.sky.courseworkJavaCore.Service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.courseworkJavaCore.Service.QuestionService;
import pro.sky.courseworkJavaCore.exceptions.IncorrectAmountOfQuestions;
import pro.sky.courseworkJavaCore.model.Question;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Collection<Question> questions = Set.of(
            new Question("Q1", "A1"),
            new Question("Q2", "A2"),
            new Question("Q3", "A3"),
            new Question("Q4", "A4"),
            new Question("Q5", "A5")
    );

    @Test
    public void getQuestionsNegativeTest() {
        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size()+1));
        assertThatExceptionOfType(IncorrectAmountOfQuestions.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    public void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);

        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Q1", "A1"),
                new Question("Q1", "A1"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4")
        );

        assertThat(examinerService.getQuestions(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Q1", "A1"),
                        new Question("Q3", "A3"),
                        new Question("Q4", "A4")
                );
    }
}
