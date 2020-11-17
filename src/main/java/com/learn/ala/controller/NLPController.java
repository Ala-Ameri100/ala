package com.learn.ala.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ala.util.Type;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class NLPController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

	
	@PostMapping
    @RequestMapping(value = "/ner")
    public Set<String> ner(@RequestBody final String input, @RequestParam final Type type) {
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> coreSentence = coreDocument.sentences();
        List<CoreLabel> coreLabels = coreDocument.tokens();
        return new HashSet<>(collectList(coreLabels, type, coreSentence));
    }

    private List<String> collectList(List<CoreLabel> coreLabels, final Type type, List<CoreSentence> coreSentences) {
    
       	System.out.println("Type---"+coreLabels.get(2).get(CoreAnnotations.NamedEntityTagAnnotation.class));
			  for(CoreSentence coreSentence:coreSentences) {
				  String ner = coreSentence.sentiment();
				  //String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				  System.out.println(coreSentence +"--Sentence Type--"+ ner);
			  }
			  
			  for(CoreLabel coreLabel:coreLabels) {
				  String ner = coreLabel.lemma();
				  String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				  System.out.println(coreLabel.originalText() +"--"+ pos);
			  }

		return coreLabels
		                .stream()
		                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
		                .map(CoreLabel::originalText)
		                .collect(Collectors.toList());
    }
}
