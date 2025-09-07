package com.Learning.First.SpringAI.service;

import com.Learning.First.SpringAI.payload.CricketResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private StreamingChatModel streamingChatModel;

    public String generateResponse(String inputText){
      String response = chatModel.call(inputText);
      return response;
    }

    public Flux<String> streamResponse(String inputText){
        Flux<String> response = streamingChatModel.stream(inputText);
        return response;
    }

    public CricketResponse generateCricketResponse(String inputTxt) throws JsonProcessingException {
        String promptString = "You are a cricket expert.\n" +
                "Answer the following question strictly within the domain of cricket: \n"
                + inputTxt + "\n\n" +
                "If the question is unrelated to cricket, politely refuse to answer.\n"
                + "Return your answer in strict JSON format with this structure:\n" +
                "{\"content\" : \"<your answer here>\"}\n" +
                "Do not include any text outside of the JSON";
        ChatResponse cricketResponse = chatModel.call(
                new Prompt(promptString)
        );
        // get content as a string
        String responseString = cricketResponse.getResult().getOutput().getText();
        ObjectMapper mapper = new ObjectMapper();
        CricketResponse r = mapper.readValue(responseString, CricketResponse.class);
        return r;
    }
}
