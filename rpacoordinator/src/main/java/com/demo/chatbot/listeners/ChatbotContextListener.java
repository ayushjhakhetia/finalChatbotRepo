package com.demo.chatbot.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ChatbotContextListener implements ServletContextListener {
	
	@Autowired
	private Environment env;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext initialized.");
		ServletContext sc = sce.getServletContext();
		System.out.println("Context Path: " + sc.getContextPath());

	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		System.out.println("ServletContext destroyed.");			
	}
} 