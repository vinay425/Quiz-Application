package com.learnings.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.learnings.quizapp.Entity.Question;
import com.learnings.quizapp.Entity.QuestionWrapper;
import com.learnings.quizapp.Entity.Quiz;
import com.learnings.quizapp.Entity.Response;
import com.learnings.quizapp.dao.QuestionsRepo;
import com.learnings.quizapp.dao.QuizRepo;
import java.util.*;


@Service
public class QuizService {
	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuestionsRepo questionsRepo;

	public ResponseEntity<String> createQuiz(String category, String title, int numQ) {
		
		List<Question> questions= questionsRepo.findRandomQuestionByCategory(category,numQ);
		
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		quizRepo.save(quiz);
		
		
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizRepo.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for (Question q : questionsFromDB) {
			QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
					q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUser.add(questionWrapper);
		}

		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getQuizResult(Integer id, List<Response> responses) {
		
		Optional<Quiz> quiz=quizRepo.findById(id);
		List<Question> questions= quiz.get().getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			i++;
			
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
