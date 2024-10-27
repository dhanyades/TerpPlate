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

import org.springframework.web.bind.annotation.*;

@RestController
public class ChatButtonController {

    private final AssistantImpl assistantImpl;

    public ChatButtonController(AssistantImpl assistantImpl) {
        this.assistantImpl = assistantImpl;
    }

    @PostMapping("/chat")
    public Map<String, String> receiveChat(@RequestBody Map<String, String> requestBody) {
        String chat = requestBody.get("chat"); // Access the input from the request body
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
