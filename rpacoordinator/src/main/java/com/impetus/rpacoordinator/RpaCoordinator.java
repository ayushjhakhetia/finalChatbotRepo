package com.impetus.rpacoordinator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpaCoordinator {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/rpacoordinator");
		SpringApplication.run(RpaCoordinator.class, args);
	}

}
