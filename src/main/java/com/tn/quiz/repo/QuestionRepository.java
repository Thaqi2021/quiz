package com.tn.quiz.repo;

import com.tn.quiz.model.exam.Question;
import com.tn.quiz.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository  extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);

//    @Query(value = "SELECT ques_id, content, image,option1,option2,option3,option4 FROM question where quiz_qid =:quizId", nativeQuery = true)
//    List<Object[]> findQuestionFields(@Param("quizId") Long quizId);
}
