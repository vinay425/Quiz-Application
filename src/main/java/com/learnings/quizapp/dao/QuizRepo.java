package com.learnings.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learnings.quizapp.Entity.Question;
import com.learnings.quizapp.Entity.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer> {
	
	
	

}
