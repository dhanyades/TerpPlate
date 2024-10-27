package com.example.langchainapp;

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
import dev.langchain4j.model.openai.OpenAiTokenizer;
import dev.langchain4j.model.chat.ChatLanguageModel;
//import dev.langchain4j.model.ollama.OllamaChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class AssistantImpl implements NutritionAssistant{

    public NutritionAssistant assistant;

    public AssistantImpl() {
        this.assistant = AiServices.builder(NutritionAssistant.class)
            .chatLanguageModel(
                OpenAiChatModel.builder()
                    .apiKey(System.getenv("OPEN_AI_API_KEY"))
                    .modelName(GPT_4_O_MINI)
                    .build())
            //.chatMemory(TokenWindowChatMemory.withMaxTokens(300, new OpenAiTokenizer()))
            .build();
    }

    public String loadMenu() {
        Document document = FileSystemDocumentLoader.loadDocument("/Users/isabellasteinebach/Desktop/Technica2024/food_copy.txt", new TextDocumentParser());

        return document.toTextSegment().text();
    }

    public String getMealGoals(String calories, String sugar, String sodium, String protein) {
        // Document document = FileSystemDocumentLoader.loadDocument("....Preferred cals, etc", new TextDocumentParser());

        // String[] parts = document.toTextSegment().text().split("\n");

        // UserMessage userMessage = UserMessage.from(
        //     TextContent.from("Meal Calorie Goal: " + parts[0] 
        //                         + "Meal Sugar Goal: " + parts[1]
        //                         + "Meal Sodium Goal: " + parts[2] 
        //                         + "Meal Protein Goal: " + parts[3]));

        String mealGoals = "Meal Calorie Goal: " + calories 
                                + "\nMeal Sugar Goal: " + sugar
                                + "\nMeal Sodium Goal: " + sodium
                                + "\nMeal Protein Goal: " + protein;

        // System.out.println("Calories: " + calories);
        // System.out.println("Sugar: " + sugar);
        // System.out.println("Sodium: " + sodium);
        // System.out.println("Protein: " + protein);

        System.out.println(mealGoals);

        return mealGoals;
    }

    public String generateMeal() {
        String menu = loadMenu();

        String systemMessage = "You are here to help college students eat healthy at the dining hall. I will provide you with the daily menu for the specific dining hall that the student is dining in, and you should generate a balanced meal based on the foods in the dining hall." + 

        "Avg calorie count... " + 
    
        "The daily dining hall menu is: " + menu

        + "Please suggest a meal for today's menu. Additionally, please tell me the nutrition facts for each food in a strcutured way. Finally, tell me the total nutrition facts for the entire meal by adding up the numbers from every ingredient.";

        System.out.println("THE MENU IS\n" + menu);
        
        // Generate the response
        String response = assistant.chat(systemMessage);
        return response;
    }

    @Override
    public String chat(String userMessage) {

        System.out.println("User message is: " + userMessage);

        String response = assistant.chat(userMessage);

        System.out.println("model answer message is: " + response);

        return response;
    }
}