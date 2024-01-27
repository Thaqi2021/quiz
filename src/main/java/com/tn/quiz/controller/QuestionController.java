package com.tn.quiz.controller;

import com.tn.quiz.model.exam.Question;
import com.tn.quiz.model.exam.Quiz;
import com.tn.quiz.service.QuestionService;
import com.tn.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question){
        return ResponseEntity.ok( this.questionService.addQuestion(question));
    }

    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long qid){
        return this.questionService.getQuestionById(qid );
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuestion(){
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question){
        return this.questionService.updateQuestion(question);

    }
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid){
//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        Set<Question> questionByQuiz=this.questionService.getQuestionByQuiz(quiz);
//        return ResponseEntity.ok(questionByQuiz);
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList<>(questions);
        Collections.shuffle(list);
        if(list.size()> quiz.getNoOfQuestion()){
            list = list.subList(0, quiz.getNoOfQuestion());
        }
        return ResponseEntity.ok(list);


    }


    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }
}
