package com.dapp.web.outng.chat;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApp {

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(ChatApp.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", (Object)"8087"));
		app.run(args);
	}

}
