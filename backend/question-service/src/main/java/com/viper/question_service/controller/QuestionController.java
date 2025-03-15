package com.viper.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viper.question_service.model.Question;
import com.viper.question_service.model.QuestionWrapper;
import com.viper.question_service.model.QuizResponse;
import com.viper.question_service.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	Environment environment;

	@GetMapping("/all")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestion(@PathVariable int questionId){
		return questionService.getQuestion(questionId);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@PutMapping
	public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
		return questionService.updateQuestion(question);
	}
	
	@DeleteMapping("/{questionId}")
	public ResponseEntity<String> deleteQuestion(@PathVariable int questionId) {
		return questionService.deleteQuestion(questionId);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<String>> getAllCategories(){
		return questionService.getAllCategories();
	}
	
	//Extra things to make this a microservice
	
	//1. Generate questions for quiz
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int noOfQuestions){
		return questionService.getQuestionsForQuiz(category, noOfQuestions);
	}
	
	//2. get question (based on question id)
	@PostMapping("/details")
	public ResponseEntity<List<QuestionWrapper>> getQuestionDetailsForQuiz(@RequestBody List<Integer> questionIds){
		System.out.println("Port = " + environment.getProperty("local.server.port"));
		return questionService.getQuestionDetailsForQuiz(questionIds);
	}
	
	//3. get score (calculate score here)
	@PostMapping("/score")
	public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses){
		return questionService.getScore(responses);
	}
}
