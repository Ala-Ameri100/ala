package com.learn.ala.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.learn.ala.model.MessageDTO;
import com.learn.ala.model.Questions;
import com.learn.ala.repository.QuestionRepository;
import com.learn.ala.service.TopicService;
import com.learn.ala.util.Util;

@Service
public class TopicServiceImpl implements TopicService{
	
	@Autowired
	QuestionRepository questionRepository;
	
	private static final String[] welcomeMessage = { "Hi! I am the Ameri100 Learning Assistant. You can call me ALA",
			"Ask a question to get started.",
			"You can ask questions from BPC, Cloud, UX, Hana CDS, SCP, MEAN stack.",
			};
	private static final String content = "Topic";

	@Override
	public List<Questions> retrieveDistinctTopics() {
		
		Collection<Questions> question = questionRepository.findAll();
		List<Questions> questions = question.stream().filter(Util.distinctByKey(q-> q.getTopic())).collect( Collectors.toList() );
		
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		 // convert your list to json
		 String jsonCartList = gson.toJson(questions);
	      System.out.println(jsonCartList);
		return questions;
	}

	@Override
	public List<String> retrieveDifficultyLevelByTopic(String topic) {
		List<String> difficultyLevel = questionRepository.findDistinctLevelByTopic(topic);
		
		return difficultyLevel;
		
	}

	@Override
	public List<Questions> retrieveQuestionsByTopic(String topic, String difficultyLevel) {
		List<Questions> questions = questionRepository.findQuestionsByTopicAndDifficultyLevel(topic, difficultyLevel);
		return questions;
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
		
		MessageDTO dto = new MessageDTO();
		dto.setMessage(content);
		dto.setClickable(Boolean.TRUE);
		listDto.add(dto);
		// TODO Auto-generated method stub
		return listDto;
	}
	
	

}
