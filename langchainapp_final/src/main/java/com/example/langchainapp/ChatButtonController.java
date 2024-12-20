package com.example.langchainapp;

import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatButtonController {

    private final AssistantImpl assistantImpl;

    public ChatButtonController(AssistantImpl assistantImpl) {
        this.assistantImpl = assistantImpl;
    }

    @PostMapping("/chat")
    public Map<String, String> receiveChat(@RequestBody Map<String, String> requestBody) {
        String chat = requestBody.get("chat"); 
        // Access the input from the request body
        System.out.println("Received chat message: " + chat);

        // You can now use the input in your AssistantImpl methods
        String answer = assistantImpl.chat(chat);
        
        // Return a response back to the client if necessary
        Map<String, String> modelResponse = new HashMap<>();
        modelResponse.put("message", "processed successfully.");
        modelResponse.put("model_response", answer);
        return modelResponse;
    }
    
}
