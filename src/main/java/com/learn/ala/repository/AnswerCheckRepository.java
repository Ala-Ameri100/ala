package com.learn.ala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.ala.model.Questions;

@Repository
public interface AnswerCheckRepository extends JpaRepository<Questions, Long>{
	
	List<Questions> findByQuestionText(String questionText);
	
	

}
