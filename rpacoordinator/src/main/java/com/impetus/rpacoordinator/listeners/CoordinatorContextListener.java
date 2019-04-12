package com.impetus.rpacoordinator.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CoordinatorContextListener implements ServletContextListener {
	
	@Autowired
	private Environment env;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext initialized.");
		ServletContext sc = sce.getServletContext();
		System.out.println("Context Path: " + sc.getContextPath());
		
		//env.
		
		//Read the Orchestrator URL from config.
		//Create a new UUID if not already stored locally.
		//Register the agent to Orchestrator. - Call Orchestrator WS
		//The Orchestrator URL & UUID should be stored in an external config which can be read by both Agent & Bot
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		System.out.println("ServletContext destroyed.");			
	}
} 