package com.viper.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.viper.quiz_service.model.QuestionWrapper;
import com.viper.quiz_service.model.QuizResponse;

@FeignClient("QUESTION-SERVICE")
public interface QuestionFeign {
	
	//1. Generate questions for quiz
	@GetMapping("/question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam int noOfQuestions);
	
	//2. get question (based on question id)
	@PostMapping("/question/details")
	public ResponseEntity<List<QuestionWrapper>> getQuestionDetailsForQuiz(@RequestBody List<Integer> questionIds);
	
	//3. get score (calculate score here)
	@PostMapping("/question/score")
	public ResponseEntity<Integer> getScore(@RequestBody List<QuizResponse> responses);
	
}
