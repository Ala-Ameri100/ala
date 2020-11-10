package com.learn.ala.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ala.model.QuestionAnswerDTO;
import com.learn.ala.model.Questions;
import com.learn.ala.repository.AnswerCheckRepository;
import com.learn.ala.service.AnswerCheckService;

@Service
public class AnswerCheckServiceImpl implements AnswerCheckService{
	
	@Autowired
	AnswerCheckRepository answerCheckRepository;

	@Override
	public List<QuestionAnswerDTO> retrieveCorrectAnswer(QuestionAnswerDTO dto) {
		
		List<Questions> questionData = answerCheckRepository.findByQuestionText(dto.getQuestion());
		
		String correctAnswer = questionData.get(0).getCorrectAnswer();
		
		return null;
		
	}

}
