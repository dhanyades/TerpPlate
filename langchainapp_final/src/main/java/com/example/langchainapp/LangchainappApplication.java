package com.example.langchainapp;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.langchain4j.*;

import dev.langchain4j.data.document.*;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.message.*;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.localai.LocalAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.model.ollama.OllamaChatModel;

import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static dev.langchain4j.model.openai.OpenAiImageModelName.DALL_E_3;


import java.util.*;
import org.testcontainers.utility.DockerImageName;

/* 
interface Assistant {

	String generateMeal();

	@SystemMessage("You are here to help college students eat healthy at the dining hall. You will be provided with the dining hall menu, so you know all the nutrition facts of different foods, and students can ask you questions about different foods and their nutritional value, as well as general questions about balanced eating.")
	String chat(String userMessage);

	UserMessage getMealGoals(String userMessage);

}*/


	// On chat bot section: 
	// Ask me questions about nutrition, eating healthy, or anything on the dining hall menu!
	// Example questions: 
	// How much protein is in one piece of bacon at the dining hall? 
	// If I eat one serving of peas, and one serving of mashed potatoes, is that a balanced meal?

@SpringBootApplication
public class LangchainappApplication {

	
	public static void main(String[] args) {		
		SpringApplication.run(LangchainappApplication.class, args);

		System.out.println("Ready to run");

		// ImageModel model = OpenAiImageModel.builder()
        //         .apiKey(System.getenv("OPEN_AI_API_KEY"))
        //         .modelName(DALL_E_3)
        //         .build();

        // Response<Image> response = model.generate("Make a pie chart like a balanced meal image, with a section for broccoli, chicken, and rice.");

		//System.out.println(response.content().url());
    }

	// public void initChatAssistant() {
	// 	return AiServices.builder(Assistant.class)
	// 		.chatLanguageModel(
	// 			OpenAiChatModel.builder()
	// 				.apiKey(System.getenv("OPEN_AI_API_KEY"))
	// 				.modelName(GPT_4_O_MINI)
	// 				.build())
	// 		.chatMemory(TokenWindowChatMemory.withMaxTokens(300, new OpenAiTokenizer()))
	// 		.build();
		
	// }

	// public void initGenerateAssistant() {
	// 	return AiServices.builder(Assistant.class)
	// 			.chatLanguageModel(
	// 				OpenAiChatModel.builder()
	// 					.apiKey(System.getenv("OPEN_AI_API_KEY"))
	// 					.modelName(GPT_4_O_MINI)
	// 					.build())
	// 			.chatMemory(TokenWindowChatMemory.withMaxTokens(300, new OpenAiTokenizer()))
	// 			.build();
	// }



	//public void createModel() {
		// ChatLanguageModel model = OpenAiChatModel.builder()
        // .apiKey(System.getenv("OPEN_AI_API_KEY"))
		// .modelName(GPT_4_O_MINI)
		// //.responseFormat("json_schema")
    	// //.strictJsonSchema(true)
        // .build();

		//Assistant assistant = AiServices.create(Assistant.class, model);


		// UserMessage userMessage = UserMessage.from(
        //             TextContent.from("What do you see?"),
        //             ImageContent.from("https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png")
        // );

		// If user presses "Generate Meal"
		//String meal = assistant.generateMeal();

		// UserMessage userMessage = UserMessage.from(TextContent.from(""));

		// Response<AiMessage> response = model.generate(userMessage);	

		// System.out.println(response.content().text() + "\n");

		// System.out.println(
		// 	"Total Tokens: " + response.tokenUsage().totalTokenCount() 
		// 	+ "\nInput tokens: " + response.tokenUsage().inputTokenCount() 
		// 	+ "\nOutput tokens: " + response.tokenUsage().inputTokenCount() 
		// 	);


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

	//}


}