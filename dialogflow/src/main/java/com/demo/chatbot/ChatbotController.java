package com.demo.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotController {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/rpacoordinator");
		SpringApplication.run(ChatbotController.class, args);
	}

}
