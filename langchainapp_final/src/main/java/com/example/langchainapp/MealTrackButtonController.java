package com.example.langchainapp;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class MealTrackButtonController {

    private final AssistantImpl assistantImpl;

    public MealTrackButtonController(AssistantImpl assistantImpl) {
        this.assistantImpl = assistantImpl;
    }

    @PostMapping("/mealTrack")
    public Map<String, String> receiveChat(@RequestBody Map<String, String> requestBody) {
        String chat = requestBody.get("mealTrack"); 
        // Access the input from the request body
        System.out.println("Received chat message: " + chat);

        // You can now use the input in your AssistantImpl methods
        String answer = assistantImpl.trackMeal(chat);
        
        // Return a response back to the client if necessary
        Map<String, String> modelResponse = new HashMap<>();
        modelResponse.put("message", "processed successfully.");
        modelResponse.put("model_response", answer);
        return modelResponse;
    }
    
}
