package com.learn.ala.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ala.repository.QuestionRepository;
import com.learn.ala.service.ProcessNLPService;
import com.learn.ala.service.TopicService;

@Service
public class ProcessNLPServiceImpl implements ProcessNLPService{

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	TopicService topicService;

	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	
	String resourcesPath = getResourcesPath();
	
	
	Bot bot = new Bot("super", resourcesPath);
	Chat chatSession = new Chat(bot);

	@Override
	public String processBoatRequest(String input) {
		
	try {
		MagicBooleans.trace_mode = TRACE_MODE;
		bot.brain.nodeStats();
		String response = null;
		

		while(true) {
			
			System.out.println("Human : "+input);
			
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
		String path = null;
		try {
			path = currDir.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Path befor lenght deduction--"+path);
		//path = path.substring(0, path.length() - 2);
		System.out.println(path);
		File folderpath = new File(path+ File.separator+"bots");
		if(folderpath.exists())
			return path;
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
	
    /*
    The resource URL is not working in the JAR
    If we try to access a file that is inside a JAR,
    It throws NoSuchFileException (linux), InvalidPathException (Windows)

    Resource URL Sample: file:java-io.jar!/json/file1.json
 */
//private File getFileFromResource(String fileName) throws URISyntaxException{
//
//    ClassLoader classLoader = getClass().getClassLoader();
//    URL resource = classLoader.getResource(fileName);
//    if (resource == null) {
//        throw new IllegalArgumentException("file not found! " + fileName);
//    } else {
//
//        // failed if files have whitespaces or special characters
//        //return new File(resource.getFile());
//
//        return new File(resource.toURI());
//    }
//
//}


	
}
