package com.tn.quiz.service.impl;

import com.tn.quiz.model.exam.Category;
import com.tn.quiz.model.exam.Quiz;
import com.tn.quiz.repo.QuizRepository;
import com.tn.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
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

    @Override
    public List<Quiz> getQuizzesOfCategory(Long cid) {
        Category category =  new Category();
        category.setCid(cid);
        return quizRepo.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuiz() {
        return quizRepo.findByIsActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizInCategory(Category cat) {
        return quizRepo.findByCategoryAndIsActive(cat,true);
    }
}
