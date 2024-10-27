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

    // Initialize per meal goals based on Average National Standards for an adult consuming around 2k calories every day
    private String calorieGoal = "600";
    private String sugarGoal = "600"; //milligrams
    private String sodiumGoal = "8"; //grams
    private String proteinGoal = "30"; //grams


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

    public String setMealGoals(String calories, String sugar, String sodium, String protein) {
        if (calories != null) {
            this.calorieGoal = calories;
        }
        if (sugar != null) {
            this.sugarGoal = sugar;
        }
        if (sodium != null) {
            this.sodiumGoal = sodium;
        }
        if (protein != null) {
            this.proteinGoal = protein;
        }

        // If meal goals are not null, then just use default values
        String mealGoals = "Meal Calorie Goal: " + calorieGoal 
                                + "\nMeal Sugar Goal: " + sugarGoal
                                + "\nMeal Sodium Goal: " + sodiumGoal
                                + "\nMeal Protein Goal: " + proteinGoal;

        System.out.println(mealGoals);

        return mealGoals;
    }

    public String generateMeal() {
        String menu = loadMenu();

        String systemMessage = "You are here to help college students eat healthy at the dining hall. I will provide you with the daily menu for the specific dining hall that the student is dining in, and you should generate a balanced meal based on the foods in the dining hall." + 

        "This student's Per Meal Nutritional Goals:\n Calorie Goal: "          + calorieGoal 
            + "\nSugar Goal: " + sugarGoal
            + "\nSodium Goal: " + sodiumGoal
            + "\nProtein Goal: " + proteinGoal + 
    
        "The daily dining hall menu is: " + menu

        + "Please suggest a meal for today's menu. Additionally, please tell me the nutrition facts for each food in a strcutured way. Finally, tell me the total nutrition facts for the entire meal by adding up the numbers from every ingredient."
        
        + "Please only output the meal and it's nutritional deals";

        //System.out.println(systemMessage);

        System.out.println("Model getting response");
        
        // Generate the response
        String response = assistant.chat(systemMessage);

        System.out.println(response);
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