package com.example.langchainapp;

import java.util.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class MealController {

    private final AssistantImpl assistantImpl;

    public MealController(AssistantImpl assistantImpl) {
        this.assistantImpl = assistantImpl;
    }

    @PostMapping("/mealGoals")
    public Map<String, String> receiveMealGoals(@RequestBody Map<String, String> requestBody) {
        String mealGoals = requestBody.get("mealGoals"); // Access the input from the request body
        System.out.println("Received meal goals: " + mealGoals);
        
        // Return a response back to the client if necessary
        Map<String, String> response = new HashMap<>();
        response.put("message", "Meal goals processed successfully.");
        return response;
    }
}
