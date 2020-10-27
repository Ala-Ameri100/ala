package com.learn.ala.service;

import java.util.List;

import com.learn.ala.model.MessageDTO;
import com.learn.ala.model.Questions;

public interface TopicService {
	
	List<Questions> retrieveDistinctTopics();
	
	List<String> retrieveDifficultyLevelByTopic(String topic);

	List<Questions> retrieveQuestionsByTopic(String topic, String difficultyLevel);

	List<MessageDTO> retrieveWelcomeMessage();

}
