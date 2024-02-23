package com.tn.quiz.controller;

import com.tn.quiz.model.exam.Question;
import com.tn.quiz.model.exam.Quiz;
import com.tn.quiz.service.QuestionService;
import com.tn.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;
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
        List<Question> list = new ArrayList<>(questions);
        list.forEach((q)->{
            q.setAnswer("");
        });
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

    @GetMapping("/quiz/question/{qid}")
    public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable("qid") Long qid){
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List list = new ArrayList<>(questions);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/eval-answer")
    public  ResponseEntity<?> getAllQuestionWithAnswer(@RequestBody List<Question> question){
       Integer correctAns=0;
       Double markGot=0.0;
       Integer attempedQuestion=0;
       Double singleMark =question.get(0).getQuiz().getMaxMark()/question.get(0).getQuiz().getNoOfQuestion();
        if(question!=null){
            for (Question ques:question) {
               Question q= this.questionService.getQuestionById(ques.getQuesId());
               System.out.println(q.getAnswer()+"...........");
               if(q.getAnswer().equals(ques.getGivenAnswer())){
                   correctAns++;
                   markGot=markGot+singleMark;
               }
                if (ques.getGivenAnswer()!=null || !ques.getGivenAnswer().trim().equals("")) {
                    attempedQuestion++;
                }

            }
            Map map = Map.of("correctAns",correctAns,"markGot",markGot,"attempedQuestion",attempedQuestion);
            return ResponseEntity.ok(map);

        }else {
            return ResponseEntity.ok("No");
        }
    }
//    @GetMapping("/onlyQuestion/{qid}")
//    public ResponseEntity<?> getOnlyQuestion(@PathVariable("qid") Long qid){
//        System.out.println("ok test.....................");
//        List<Question> questions =this.questionService.getQuestionFields(qid);
//        return ResponseEntity.ok(questions);
//    }
}
