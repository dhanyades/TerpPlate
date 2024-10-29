package com.example.langchainapp;

import dev.langchain4j.data.document.*;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Component;

@Component
public class AssistantImpl implements NutritionAssistant{

    public NutritionAssistant assistant;

    // Initialize per meal goals based on Average National Standards for an adult consuming around 2k calories every day
    private String calorieGoal = "650";
    private String sugarGoal = "10"; //grams
    private String sodiumGoal = "550"; //milligrams
    private String proteinGoal = "20"; //grams

    private String menu;


    public AssistantImpl() {
        this.assistant = AiServices.builder(NutritionAssistant.class)
            .chatLanguageModel(
                OpenAiChatModel.builder()
                    .apiKey(System.getenv("OPEN_AI_API_KEY"))
                    .modelName(GPT_4_O_MINI)
                    .temperature(0.2)
                    .build())

            .build();
        
        this.menu = loadMenu();      
    }

    public String loadMenu() {
        Document document = FileSystemDocumentLoader.loadDocument("/Users/isabellasteinebach/Desktop/Technica2024/food.txt", new TextDocumentParser());

        return document.toTextSegment().text();
    }

    public String setMealGoals(String calories, String sugar, String sodium, String protein) {

        if (calories != null && !calories.isEmpty())  {

            this.calorieGoal = calories;
        }
        if (sugar != null && !sugar.isEmpty()) {
            this.sugarGoal = sugar;
        }
        if (sodium != null && !sodium.isEmpty()) {
            this.sodiumGoal = sodium;
        }
        if (protein != null && !protein.isEmpty()) {
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

        String userMessage = "You are here to help college students eat healthy at the dining hall. " + 

        "This particular student's Per Meal Nutritional Goals:\n Calorie Goal (cals): "          + calorieGoal 
            + "\nSugar Goal (grams): " + sugarGoal
            + "\nSodium Goal (milligrams): " + sodiumGoal
            + "\nProtein Goal (grams): " + proteinGoal + 
            " Create the meal based on these goals." + 
    
        "The daily dining hall menu is: " + this.menu

        + "Now that you can see the daily menu for the dining hall, generate a meal based on the menu, so the nutritional facts of each food will add up to meet the students goals. DO NOT generate more than one version of the meal or say that you need to make adjustments, only suggest one meal. \n "
        
        + "Return your response in the format: " + 

            "Your Nutritional Goals: \n Calories:[Num calories] \n Sugar: [Sugar] grams \n Sodium:[Sodium] milligrams \n Protein:[Protein] grams" + 
        
            "Recommended Meal: \n Food 1:[Name of food] \n Calories:[Num calories (cals)] \n Sugar: [Grams of sugar (g)] \n Sodium:[mg of sodium] \n Protein:[grams of protein] \n\n" + 
            "Food n:..., Calories:..., Sugar: ...," + 

            "Total Nutrition Facts for the Entire Meal: ...";

        System.out.println("Model getting response");
        
        // Generate the response
        return assistant.chat(userMessage);
    }

    @Override
    public String chat(String userMessage) {
        String chat = "You are here to help college students eat healthy at the dining hall. I will provide you with the daily menu for the specific dining hall that the student is dining in." + 

        "This student's Per Meal Nutritional Goals:\n Calorie Goal: "          + calorieGoal 
            + "\nSugar Goal: " + sugarGoal
            + "\nSodium Goal: " + sodiumGoal
            + "\nProtein Goal: " + proteinGoal + 
    
        "The daily dining hall menu is: " + this.menu

        + "Please answer the students following question:\n " + userMessage;

        return assistant.chat(chat);
    }

    @Override
    public String trackMeal(String userMessage) {
        String chat = "Students want to know the nutritional value of their meal. They will tell you what they are eating, and you will tell them the total nutritional facts of their food based on the menu, and if it meets their goals" + 

        "This student's Per Meal Nutritional Goals:\n Calorie Goal: "          + calorieGoal 
            + "\nSugar Goal: " + sugarGoal
            + "\nSodium Goal: " + sodiumGoal
            + "\nProtein Goal: " + proteinGoal + 
    
        "The daily dining hall menu is: " + this.menu

        + "This is the student's meal:\n " + userMessage + "\n What are the total nutritional facts of these foods";

        return assistant.chat(chat);
    }
}