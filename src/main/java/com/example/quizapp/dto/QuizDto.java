package com.example.quizapp.dto;

import com.example.quizapp.entity.Quiz;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.TinyIntAsSmallIntJdbcType;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long id;

    @NotBlank(message = "공백일 수 없습니다")
    private String quiz;

    private Boolean answer;

    private String user;

    public static QuizDto fromQuizEntity(Quiz quiz){
        return new QuizDto(
                quiz.getId(),
                quiz.getQuiz(),
                quiz.getAnswer(),
                quiz.getUser()
        );
    }

    public Quiz fromQuizDto(QuizDto dto){
        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setQuiz(dto.getQuiz());
        quiz.setAnswer(dto.getAnswer());
        quiz.setUser(dto.getUser());
        return quiz;
    }
}
