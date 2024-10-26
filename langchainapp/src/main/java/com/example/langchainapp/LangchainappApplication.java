package com.example.langchainapp;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.langchain4j.*;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.localai.LocalAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.model.ollama.OllamaChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;

import java.util.*;
import org.testcontainers.utility.DockerImageName;
import dev.langchain4j.data.message.UserMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.*;




@SpringBootApplication
public class LangchainappApplication {

	// public interface ChatLanguageModel {
	// 	String generate(String userMessage);	
	// }


	public String loadMenu() {
		return "hi";
	}


	interface Assistant {

		@SystemMessage("You are here to help college students eat healthy at the dining hall. I will provide you with the daily menu for the specific dining hall that the student is dining in, and you should generate a balanced meal based on the foods in the dining hall." + 
		
		"  Avg calorie count... " + 

		" The daily dining hall menu is: "
		)
		String generateMeal();

		@SystemMessage("You are here to help college students eat healthy at the dining hall. You will be provided with the dining hall menu, so you know all the nutrition facts of different foods, and students can ask you questions about different foods and their nutritional value")
		String chat(String userMessage);

		// On the home page: 
		// Ask me questions about nutrition, eating healthy, or anything on the dining hall menu!
		// Example questions: 
		// How much protein is in one piece of bacon at the dining hall? 
		// If I eat one serving of peas, and one serving of mashed potatoes, is that a balanced meal?
	}

	public static void main(String[] args) {
		//SpringApplication.run(LangchainappApplication.class, args);

		ChatLanguageModel model = OpenAiChatModel.builder()
        .apiKey(System.getenv(OPEN_AI_API_KEY))
		.modelName(GPT_4_O_MINI)
		//.responseFormat("json_schema")
    	//.strictJsonSchema(true)
        .build();

		Assistant assistant = AiServices.create(Assistant.class, model);
		// UserMessage userMessage = UserMessage.from(
        //             TextContent.from("What do you see?"),
        //             ImageContent.from("https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png")
        // );

		// If user presses "Generate Meal"
		//String meal = assistant.generateMeal();

		UserMessage userMessage = UserMessage.from(TextContent.from(""));

		Response<AiMessage> response = model.generate(userMessage);	

		System.out.println(response.content().text() + "\n");

		System.out.println(
			"Total Tokens: " + response.tokenUsage().totalTokenCount() 
			+ "\nInput tokens: " + response.tokenUsage().inputTokenCount() 
			+ "\nOutput tokens: " + response.tokenUsage().inputTokenCount() 
			);


		/* 
		// Reading JSON file 
		ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read the JSON file into a List of Strings
            List<String> titles = objectMapper.readValue(new File("scraped_data.json"), new TypeReference<List<String>>() {});

            // Do something with the data (e.g., print it)
            System.out.println("Scraped Titles:");
            for (String title : titles) {
                System.out.println(title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		*/

    }

}
