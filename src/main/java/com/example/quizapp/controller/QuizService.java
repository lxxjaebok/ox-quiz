package com.example.quizapp.controller;

import com.example.quizapp.dto.QuizDto;
import com.example.quizapp.entity.Quiz;
import com.example.quizapp.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public List<QuizDto> quizList() {
        List<QuizDto> quizDtoList = new ArrayList<>();
        return quizRepository.findAll()
                .stream()
                .map(x->QuizDto.fromQuizEntity(x))
                .toList();
    }


    public QuizDto getOneQuiz(Long id) {
        return quizRepository.findById(id)
                .map(x -> QuizDto.fromQuizEntity(x))
                .orElse(null);
    }

    public void insertQuiz(QuizDto dto){
        Quiz quiz = dto.fromQuizDto(dto);
        quizRepository.save(quiz);
    }

    public void quizDelete(Long id) {
        quizRepository.deleteById(id);
    }

    public QuizDto playQuiz() {
        return QuizDto.fromQuizEntity(quizRepository.random());
    }
}
