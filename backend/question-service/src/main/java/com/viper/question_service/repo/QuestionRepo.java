package com.viper.question_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viper.question_service.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{
	
	List<Question> findByCategory(String category);
	
	@Query("select q.id from Question q where q.category = :category order by random() limit :noOfQuestions")
	List<Integer> findRandomQuestionsByCategory(String category, int noOfQuestions);

	@Query("select distinct(q.category) from Question q")
	List<String> findAllCategories();
	
}
