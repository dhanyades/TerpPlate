package com.example.langchainapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.langchainapp.AssistantImpl;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.*;
import java.util.*;


@RestController
public class ButtonController {

    private final NutritionAssistant chatAssistant;

    //@Autowired
    public ButtonController(){
        this.chatAssistant = new AssistantImpl();;
        //this.generateAssistant = generateAssistant;
    }

    @PostMapping("/trigger")
    public Map<String, String> handleButtonPress() {
        // Code to trigger your Java event

        String meal = chatAssistant.generateMeal();

        System.out.println("Button was pressed!");

       System.out.println(meal);

        // Create a response to send back to the client
        Map<String, String> response = new HashMap<>();
        response.put("message", "Event triggered successfully");
        response.put("meal", meal);
        return response;
    }

    

}