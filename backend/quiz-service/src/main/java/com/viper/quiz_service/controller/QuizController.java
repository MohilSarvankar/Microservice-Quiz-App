package com.viper.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viper.quiz_service.model.QuestionWrapper;
import com.viper.quiz_service.model.Quiz;
import com.viper.quiz_service.model.QuizDto;
import com.viper.quiz_service.model.QuizResponse;
import com.viper.quiz_service.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Quiz>> getAllQuizzes(){
		return quizService.getAllQuizzes();
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNoOfQuestions(), quizDto.getTitle());
	}
	
//	@GetMapping("/{quizId}")
//	public ResponseEntity<Quiz> getQuiz(@PathVariable int quizId){
//		return quizService.getQuiz(quizId);
//	}
	
	@DeleteMapping("/{quizId}")
	public ResponseEntity<String> deleteQuiz(@PathVariable int quizId){
		return quizService.deleteQuiz(quizId);
	}
	
	@GetMapping("/{quizId}/questions")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int quizId){
		return quizService.getQuizQuestions(quizId);
	}
	
	@PostMapping("/{quizId}/submit")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int quizId, @RequestBody List<QuizResponse> quizResponses){
		return quizService.submitQuiz(quizId, quizResponses);
	}
}
