package com.tn.quiz.service.impl;

import com.tn.quiz.model.exam.Quiz;
import com.tn.quiz.repo.QuizRepository;
import com.tn.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepo;


    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new LinkedHashSet<>(quizRepo.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return quizRepo.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {
            quizRepo.deleteById(quizId);
    }
}
