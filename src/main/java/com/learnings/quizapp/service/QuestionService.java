package com.learnings.quizapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learnings.quizapp.Entity.Question;
import com.learnings.quizapp.dao.QuestionsRepo;

@Service
public class QuestionService {
	
	@Autowired
	QuestionsRepo questionsRepo;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionsRepo.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionsRepo.findByCategory(category),HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> add(Question data) {
		try {
			questionsRepo.save(data);
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failure",HttpStatus.NOT_ACCEPTABLE);
		
	}

	public ResponseEntity<String> delete(int id) {
		try {
			questionsRepo.deleteById(id);
			return new ResponseEntity<>("Success",HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Failure",HttpStatus.NOT_MODIFIED);
	}

	
}
