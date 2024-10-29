package com.example.langchainapp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class ButtonController {

    private final NutritionAssistant chatAssistant;

    //@Autowired
    public ButtonController(){
        this.chatAssistant = new AssistantImpl();;
    }

    @PostMapping("/trigger")
    public Map<String, String> handleButtonPress() {
        String meal = chatAssistant.generateMeal();

        System.out.println("Generated meal recieved");

        // Create a response to send back to the client
        Map<String, String> response = new HashMap<>();
        response.put("message", "Event triggered successfully");
        response.put("meal", meal);
        return response;
    }

}