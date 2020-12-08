package com.learn.ala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.ala.model.Questions;

@Repository
public interface QuestionRepository extends JpaRepository<Questions, Long>{
	
	@Query(value="select distinct q.difficulty_level from Questions q where q.topic =:topic",nativeQuery = true)
	List<String> findDistinctLevelByTopic(String topic);
	
	@Query(value="select distinct q.topic from Questions q where q.topic =:topic",nativeQuery = true)
	List<String> findDistinctTopic(String topic);
	
	
	@Query(value="select * from Questions q where q.topic =:topic and q.difficulty_level=:difficultyLevel",nativeQuery = true)
	List<Questions> findQuestionsByTopicAndDifficultyLevel(String topic, String difficultyLevel);

}
