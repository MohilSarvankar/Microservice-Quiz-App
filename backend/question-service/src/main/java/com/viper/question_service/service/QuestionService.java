package com.viper.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.viper.question_service.model.Question;
import com.viper.question_service.model.QuestionWrapper;
import com.viper.question_service.model.QuizResponse;
import com.viper.question_service.repo.QuestionRepo;

@Service
public class QuestionService {
	
	@Autowired
	QuestionRepo repo;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public ResponseEntity<Question> getQuestion(int questionId) {
		try {
			Question question = repo.findById(questionId).orElse(null);
			if(question == null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(question ,HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	public ResponseEntity<List<Question>>  getQuestionsByCategory(String category) {
		try{
			return new ResponseEntity<>(repo.findByCategory(category),HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	public ResponseEntity<String> addQuestion(Question question) {
		try{
			repo.save(question);
			return new ResponseEntity<>("Successfully created",HttpStatus.CREATED);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public ResponseEntity<String> updateQuestion(Question question) {
		try{
			repo.save(question);
			return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
		}		
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<String> deleteQuestion(int questionId) {
		try{
			repo.deleteById(questionId);
			return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, int noOfQuestions) {
		try{
			return new ResponseEntity<>(repo.findRandomQuestionsByCategory(category, noOfQuestions),HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}


	public ResponseEntity<List<QuestionWrapper>> getQuestionDetailsForQuiz(List<Integer> questionIds) {
		try{
			List<QuestionWrapper> wrappers = new ArrayList<>();
			List<Question> questions = repo.findAllById(questionIds);
			
			for(Question q: questions) {
				QuestionWrapper w = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
				wrappers.add(w);
			}
			
			return new ResponseEntity<>(wrappers,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	public ResponseEntity<Integer> getScore(List<QuizResponse> responses) {
		try{
			int marks = 0;
			
			for(QuizResponse qr: responses) {
				Question q = repo.findById(qr.getId()).get();
				if(q.getAnswer().equals(qr.getResponse()))
					marks++;
			}
			return new ResponseEntity<>(marks, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
