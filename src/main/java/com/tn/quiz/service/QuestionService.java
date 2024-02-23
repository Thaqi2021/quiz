package com.tn.quiz.service;

import com.tn.quiz.model.exam.Category;
import com.tn.quiz.model.exam.Question;
import com.tn.quiz.model.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    public Question addQuestion(Question question);
    public  Question updateQuestion(Question question);

    public Set<Question> getQuestions();
    public Question getQuestionById(Long questionId);

    public Set<Question> getQuestionByQuiz(Quiz quiz);

    public void deleteQuestion(Long quesId);

    //public List<Question> getQuestionFields(Long quizId);


}
