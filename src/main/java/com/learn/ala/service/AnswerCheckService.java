package com.learn.ala.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learn.ala.model.QuestionAnswerDTO;

@Service
public interface AnswerCheckService {
	
     List<QuestionAnswerDTO> retrieveCorrectAnswer(QuestionAnswerDTO dto);

}