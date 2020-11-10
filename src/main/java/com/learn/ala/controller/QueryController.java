package com.learn.ala.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.learn.ala.exception.ResourceNotFoundException;
import com.learn.ala.model.MessageDTO;
import com.learn.ala.model.QuestionAnswerDTO;
import com.learn.ala.model.Questions;
import com.learn.ala.repository.QuestionRepository;
import com.learn.ala.service.AnswerCheckService;
import com.learn.ala.service.TopicService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class QueryController {
	@Autowired
	private QuestionRepository employeeRepository;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private AnswerCheckService answerCheckService;  

	
	@GetMapping("/welcome")
	public ResponseEntity<List<MessageDTO>> retrieveWelcomeMessage()
			throws ResourceNotFoundException {
		List<MessageDTO> message = topicService.retrieveWelcomeMessage();
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/getTopics")
	public ResponseEntity<List<Questions>> getTopics()
			throws ResourceNotFoundException {
		List<Questions> questions = topicService.retrieveDistinctTopics();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	@PostMapping("/getDifficultyLevelByTopic")
	public ResponseEntity<List<String>> retrieveDifficultyLevel(@RequestParam(value = "topic") String topic) {
		System.out.println("TOPIC--"+topic);
		List<String> difficultyLevel = topicService.retrieveDifficultyLevelByTopic(topic);
		return new ResponseEntity<>(difficultyLevel, HttpStatus.OK);
	}
	
	@PostMapping("/getQuestionByTopicAndLevel")
	public ResponseEntity<List<Questions>> retrieveQuestion(@RequestParam(value = "topic") String topic, @RequestParam(value = "difficulty_level") String difficultyLevel) {
		List<Questions> difficultyLevels = topicService.retrieveQuestionsByTopic(topic, difficultyLevel);
		return new ResponseEntity<>(difficultyLevels, HttpStatus.OK);
	}

	@PostMapping("/getAnswerCheck")
	public ResponseEntity<List<Questions>> retrieveCorrectAnswer(@RequestBody QuestionAnswerDTO questionAnswerDto) {
		
		answerCheckService.retrieveCorrectAnswer(questionAnswerDto);
		List<Questions> difficultyLevels = null;
		return new ResponseEntity<>(difficultyLevels, HttpStatus.OK);
	}

}
