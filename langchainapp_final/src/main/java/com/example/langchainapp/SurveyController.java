package com.example.langchainapp;

import java.util.*;

import org.springframework.web.bind.annotation.*;


@RestController
public class SurveyController {

    private final AssistantImpl assistantImpl;

    public SurveyController(AssistantImpl assistantImpl) {
        this.assistantImpl = assistantImpl;
    }

    @PostMapping("/submit-survey")
    public Map<String, String> handleSurvey(@RequestBody Map<String, String> surveyData) {
        String calories = surveyData.get("calories");
        String sugar = surveyData.get("sugar");
        String sodium = surveyData.get("sodium");
        String protein = surveyData.get("protein");

        String mealGoalsString = "Meal Calorie Goal: " + calories 
        + "\nMeal Sugar Goal: " + sugar
        + "\nMeal Sodium Goal: " + sodium
        + "\nMeal Protein Goal: " + protein;

        System.out.println(mealGoalsString);

        // Process the survey data here
        //System.out.println("Received survey data: " + surveyData);

        assistantImpl.setMealGoals(calories, sugar, sodium, protein);

        String modelResponse = assistantImpl.generateMeal();

        if (modelResponse != null) {
            System.out.println("Got model response in survey");
        }

        // Return a response to the client
        Map<String, String> response = new HashMap<>();
        response.put("message", "Survey data processed successfully.");
        // response.put("calories", calories);
        // response.put("sugar", sugar);
        // response.put("sodium", sodium);
        // response.put("protein", protein);
        response.put("model_response", modelResponse);

        return response;
    }
}