package com.learn.ala.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.learn.ala.model.MessageDTO;
import com.learn.ala.model.Questions;
import com.learn.ala.repository.QuestionRepository;
import com.learn.ala.service.TopicService;
import com.learn.ala.util.Util;

@Service
@Scope("prototype")
public class TopicServiceImpl implements TopicService{
	
	@Autowired
	QuestionRepository questionRepository;
    List<Questions> questions = new ArrayList<Questions>();
	
	private static final String[] welcomeMessage = { "Hi! I am the Ameri100 Learning Assistant. You can call me ALA" };

	@Override
	public List<Questions> retrieveDistinctTopics() {
		
		Collection<Questions> question = questionRepository.findAll();
		questions = question.stream().filter(Util.distinctByKey(q-> q.getTopic())).collect( Collectors.toList() );
		
		return questions;
	}

	@Override
	public List<String> retrieveDifficultyLevelByTopic(String topic) {
		List<String> difficultyLevel = questionRepository.findDistinctLevelByTopic(topic);
		
		return difficultyLevel;
		
	}

	@Override
	public List<Questions> retrieveQuestionsByTopic(String topic, String difficultyLevel) {
		List<Questions> question = questionRepository.findQuestionsByTopicAndDifficultyLevel(topic, difficultyLevel);
		return question;
	}

	@Override
	public List<MessageDTO> retrieveWelcomeMessage() {
		
		HashMap<String,Boolean> messages = new HashMap<String, Boolean>();
		System.out.println(welcomeMessage[0]);
		List<MessageDTO> listDto = new ArrayList<MessageDTO>();
		for(String message:welcomeMessage) {
			MessageDTO dto = new MessageDTO();

			dto.setMessage(message);
			dto.setClickable(Boolean.FALSE);
			listDto.add(dto);
		}
		return listDto;
	}
	
	@Override
	public List<Questions> getTopics() {
		
		Collection<Questions> question = questionRepository.findAll();
		questions = question.stream().filter(Util.distinctByKey(q-> q.getTopic())).collect( Collectors.toList() );
		
		return questions;
		
	}

}
