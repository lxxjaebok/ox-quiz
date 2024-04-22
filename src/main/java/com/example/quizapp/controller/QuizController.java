package com.example.quizapp.controller;

import com.example.quizapp.dto.QuizDto;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log
@Controller
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public String quiz(Model model) {
        List<QuizDto> quizDtoList = quizService.quizList();
        model.addAttribute("quizDto", quizDtoList);
        return "quizList";
    }

    @GetMapping("/quiz/insert")
    public String insertForm(Model model) {
        model.addAttribute("quizDto", new QuizDto());
        return "insertQuiz";
    }

    @PostMapping("/quiz/insert")
    public String insertQuiz(@Valid @ModelAttribute("quizDto") QuizDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("ERROR !  ! ! ! ");
            return "insertQuiz";
        }
        quizService.insertQuiz(dto);
        return "redirect:/quiz";
    }

    @GetMapping("/quiz/update")
    public String updateMember(@RequestParam("updateId") Long id, Model model) {
        QuizDto quizDto = quizService.getOneQuiz(id);
        model.addAttribute("quizDto", quizDto);
        return "updateQuiz";
    }

    @PostMapping("/quiz/update")
    public String update(@Valid @ModelAttribute("quizDto") QuizDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "updateQuiz";
        }
        quizService.insertQuiz(dto);
        return "redirect:/quiz";
    }

    @PostMapping("/quiz/delete")
    private String delete(@RequestParam("deleteId") Long id) {
        quizService.quizDelete(id);
        return "redirect:/quiz";
    }

    @GetMapping("/quiz/play")
    public String play(Model model){
        try{
            QuizDto dto = quizService.playQuiz();
            model.addAttribute("dto", dto);
            return "playQuiz";
        }catch (Exception e){
            model.addAttribute("text","등록된 문제가 없습니다");
            return "checkQuiz";
        }
    }

    @PostMapping("/quiz/check")
    public String check(@RequestParam("answer")boolean answer,
                        @RequestParam("dtoAnswer")boolean dtoAnswer,
                        Model model){
        if (dtoAnswer == answer){
            model.addAttribute("text","정답입니다");
        }else {
            model.addAttribute("text","오답입니다");
        }
        return "checkQuiz";
    }
}
