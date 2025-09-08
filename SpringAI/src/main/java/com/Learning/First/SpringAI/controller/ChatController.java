package com.Learning.First.SpringAI.controller;

import com.Learning.First.SpringAI.payload.CricketResponse;
import com.Learning.First.SpringAI.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/ai/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;


    @GetMapping
    public ResponseEntity<String> generateResponse(
            @RequestParam( value = "inputText" , required = true) String inputText
    ){
        String responseText = chatService.generateResponse(inputText);
        return ResponseEntity.ok(responseText);
    }

    @GetMapping("/stream")
    public Flux<String> streamResponse(
            @RequestParam(value = "inputText") String inputText
    ){
       Flux<String> responseText = chatService.streamResponse(inputText);
       return responseText;
    }

    @GetMapping("/cricketbot")
    public ResponseEntity<CricketResponse> getCricketResponse(
            @RequestParam("inputText") String inputText
    ) throws JsonProcessingException{
        CricketResponse cricketResponse = chatService.generateCricketResponse(inputText);
        return ResponseEntity.ok(cricketResponse);
    }
}
