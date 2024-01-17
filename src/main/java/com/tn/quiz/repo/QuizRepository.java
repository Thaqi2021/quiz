package com.tn.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.tn.quiz.model.exam.Quiz;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
