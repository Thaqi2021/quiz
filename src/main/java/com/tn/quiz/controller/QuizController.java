package com.tn.quiz.controller;

import com.tn.quiz.model.exam.Category;
import com.tn.quiz.model.exam.Quiz;
import com.tn.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz){
        return ResponseEntity.ok( this.quizService.addQuiz(quiz));
    }

    @GetMapping("/{qid}")
    public Quiz get(@PathVariable("qid") Long qid){
        return this.quizService.getQuiz(qid );
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @PutMapping("/")
    public Quiz updateQuiz(@RequestBody Quiz quiz){
        return this.quizService.updateQuiz(quiz);

    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId){
        System.out.println("delete Quiz");
        this.quizService.deleteQuiz(quizId);
    }
}