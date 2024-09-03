package com.frodo.controller;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final ChatClient chatClient;

    Controller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping 
    String hello() {
        return "hello";
    }

    @GetMapping("/oo")
    Map<String, String> oo() {
        System.out.println("entering oo..");
        long ts = System.currentTimeMillis();
        var reply = chatClient
                .prompt()
                .user("""
                        Explain to be principles of object oriented programming.
                        Be concise. don't send anything except object oriented design.
                        """)
                .call()
                .content();
        long tu = System.currentTimeMillis();
        System.out.println(String.format("time used in oo %s ms.", tu-ts));
        return Map.of("oo", reply);
    }

    @GetMapping("/eda")
    String eda() {
        System.out.println("entering eda..");
        long ts = System.currentTimeMillis();
        var reply = chatClient
                .prompt()
                .user("""
                        Explain to be principles of event driven architecure.
                        Be concise. Specifically explain to me event storming and and bounded context.
                        Answer in 3 paragraphs with about 100 words in each
                        """)
                .call()
                .content();
        long tu = System.currentTimeMillis();
        System.out.println(String.format("time used in eda %s ms.", tu-ts));
        return reply;
    }

}
