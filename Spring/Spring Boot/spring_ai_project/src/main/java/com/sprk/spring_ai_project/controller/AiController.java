package com.sprk.spring_ai_project.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ai")
public class AiController {
    private final ChatClient chatClient;
    public AiController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    @GetMapping("/ask")
    public String askAi(@RequestParam String message){

        return chatClient.prompt().user(message).call().content();
    }
}
