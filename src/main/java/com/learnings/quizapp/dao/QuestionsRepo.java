package com.learnings.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learnings.quizapp.Entity.Question;

@Repository
public interface QuestionsRepo extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);
	
	@Query(value = "SELECT * FROM public.question q where q.category=:category order by random() limit :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(String category, int numQ);

}
