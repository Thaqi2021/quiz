package com.tn.quiz.service.impl;

import com.tn.quiz.model.exam.Question;
import com.tn.quiz.model.exam.Quiz;
import com.tn.quiz.repo.QuestionRepository;
import com.tn.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new LinkedHashSet<>(questionRepository.findAll());
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionByQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }
}
