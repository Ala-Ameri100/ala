package com.learn.ala.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learn.ala.nlp_application.core.Pipeline;
import com.learn.ala.service.ProcessNLPService;
import com.learn.ala.util.Type;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class NLPController {

	@Autowired
	ProcessNLPService processNLPService;
	
	public final String urlResource = "http://localhost:8080/api/v1/ner";

//	@PostMapping
//	@RequestMapping(value = "/ner")
//	public List<String> ner(@RequestBody final String input, @RequestParam final Type type) {
//		return processNLPService.processNLP(input, type);
//
//    }
//	
	@PostMapping
	@RequestMapping(value = "/boatResponse")
	public String boatResponse(@RequestBody final String input) {
		return processNLPService.processBoatRequest(input);

    }

	private List<String> collectList(List<CoreLabel> coreLabels, final Type type, List<CoreSentence> coreSentences) {

		System.out.println("Type---" + coreLabels.get(2).get(CoreAnnotations.NamedEntityTagAnnotation.class));
		for (CoreSentence coreSentence : coreSentences) {
			String ner = coreSentence.sentiment();
			// String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
			System.out.println(coreSentence + "--Sentence Type--" + ner);
		}

		for (CoreLabel coreLabel : coreLabels) {
			String ner = coreLabel.lemma();
			String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
			System.out.println(coreLabel.originalText() + "--" + pos);
		}

		return coreLabels.stream()
				.filter(coreLabel -> type.getName()
						.equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
				.map(CoreLabel::originalText).collect(Collectors.toList());
	}
}
