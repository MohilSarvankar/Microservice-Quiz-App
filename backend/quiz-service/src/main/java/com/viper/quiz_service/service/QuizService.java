package com.viper.quiz_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.viper.quiz_service.feign.QuestionFeign;
import com.viper.quiz_service.model.QuestionWrapper;
import com.viper.quiz_service.model.Quiz;
import com.viper.quiz_service.model.QuizDto;
import com.viper.quiz_service.model.QuizResponse;
import com.viper.quiz_service.repo.QuizRepo;

@Service
public class QuizService {
	
	@Autowired
	QuizRepo quizRepo; 
	
	@Autowired
	QuestionFeign questionFeign;
	
	
	public ResponseEntity<List<Quiz>> getAllQuizzes() {
		try {
			List<Quiz> quizzes = quizRepo.findAll();
			return new ResponseEntity<>(quizzes,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
		try{
			//we have to call generate url of question service
			List<Integer> questions = questionFeign.getQuestionsForQuiz(category, noOfQuestions).getBody();
			
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			quizRepo.save(quiz);
			
			return new ResponseEntity<>("Quiz created",HttpStatus.CREATED);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizId) {
		try{
			Quiz quiz = quizRepo.findById(quizId).orElse(null);
			
			if(quiz == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			List<Integer> questionsFromDb = quiz.getQuestions();
			List<QuestionWrapper> questionsForUser = questionFeign.getQuestionDetailsForQuiz(questionsFromDb).getBody();
			
			return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<Integer> submitQuiz(int quizId, List<QuizResponse> quizResponses) {
		try{
			Quiz quiz = quizRepo.findById(quizId).orElse(null);
			if(quiz == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			int marks = questionFeign.getScore(quizResponses).getBody();
					
			return new ResponseEntity<>(marks, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
