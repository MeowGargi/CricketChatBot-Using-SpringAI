package com.Learning.First.SpringAI;

import com.Learning.First.SpringAI.payload.CricketResponse;
import com.Learning.First.SpringAI.service.ChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiProjectApplicationTests {

	@Autowired
	private ChatService chatService;

	@Test
	void responseGenerator() throws JsonProcessingException {
		CricketResponse c = chatService.generateCricketResponse("Who is sachin?");
		System.out.println(c.getContent());
	}


}
