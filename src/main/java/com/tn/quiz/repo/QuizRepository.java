package com.tn.quiz.repo;

import com.tn.quiz.model.exam.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import  com.tn.quiz.model.exam.Quiz;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {

    List<Quiz> findByCategory(Category cat);
}
