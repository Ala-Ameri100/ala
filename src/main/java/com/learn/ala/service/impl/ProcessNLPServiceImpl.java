package com.learn.ala.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.learn.ala.model.Questions;
import com.learn.ala.nlp_application.core.Pipeline;
import com.learn.ala.repository.QuestionRepository;
import com.learn.ala.service.ProcessNLPService;
import com.learn.ala.service.TopicService;
import com.learn.ala.util.Type;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import io.jsonwebtoken.lang.Collections;

@Service
public class ProcessNLPServiceImpl implements ProcessNLPService{

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	TopicService topicService;

	public static String URL_RESOURCE = "http://localhost:8080/api/v1/ner";
	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	
	String resourcesPath = getResourcesPath();
	
	
	Bot bot = new Bot("super", resourcesPath);
	Chat chatSession = new Chat(bot);

//	@Override
//	public List<String> processNLP(String input, Type type) {
//		
//		StanfordCoreNLP stanfordCoreNLP = Pipeline.getInstance();
//		  RestTemplate restTemplate = new RestTemplate();
//		  
//		  List<Questions> topicToSelect = null;
//		  List<String> difficultyLevel = new ArrayList<String>();
//		  
//		  HttpHeaders headers = new HttpHeaders(); //
//		  headers.setContentType(MediaType.APPLICATION_JSON); 
//		  MultiValueMap<String, String> map = new LinkedMultiValueMap<>(); 
//		  map.add("input", input);
//		  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//
//		  ResponseEntity<List<String>> res = restTemplate.exchange(URL_RESOURCE + "?type="+type, HttpMethod.POST, request, new ParameterizedTypeReference<List<String>>() { });
//		 if(!CollectionUtils.isEmpty(res.getBody()))
//			{
//				topicToSelect = topicService.getTopics();
//
//				for (String top : res.getBody()) {
//
//					topicToSelect.stream().filter(topic -> top.equalsIgnoreCase(topic.getTopic()))
//							.collect(Collectors.toList());
//					if (!CollectionUtils.isEmpty(topicToSelect)) {
//						difficultyLevel = topicService.retrieveDifficultyLevelByTopic(topicToSelect.get(0).getTopic());
//						return difficultyLevel;
//					}
//				}
//		 }
////		  if(!StringUtils.isEmpty(topicToSelect)) {
////			  difficultyLevel = topicService.retrieveDifficultyLevelByTopic(topicToSelect.get().getTopic());
////			  return difficultyLevel;
////		  }
//		  
//		  difficultyLevel.add("We are not suppose to answer this query");
//		  		
//		  return difficultyLevel;
//	}

	@Override
	public String processBoatRequest(String input) {
		
	try {
//		String resourcesPath = getResourcesPath();
//		System.out.println("ResourcePath-: "+resourcesPath);
//		MagicBooleans.trace_mode = TRACE_MODE;
//		Bot bot = new Bot("super", resourcesPath);
//		Chat chatSession = new Chat(bot);
		MagicBooleans.trace_mode = TRACE_MODE;
		bot.brain.nodeStats();
		String response = null;
		

		while(true) {
			System.out.print("Human : ");
			
			if ((input == null) || (input.length() < 1))
				input = MagicStrings.null_input;
			if (input.equals("q")) {
				System.exit(0);
			} else if (input.equals("wq")) {
				bot.writeQuit();
				System.exit(0);
			} else {
				String request = input;
				if (MagicBooleans.trace_mode)
					System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
				response = chatSession.multisentenceRespond(request);
				while (response.contains("&lt;"))
					response = response.replace("&lt;", "<");
				while (response.contains("&gt;"))
					response = response.replace("&gt;", ">");
				System.out.println("Robot : " + response);
			}
			return response;
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
		
	}
	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}

}
