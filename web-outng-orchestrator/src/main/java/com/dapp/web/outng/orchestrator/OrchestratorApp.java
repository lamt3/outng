package com.dapp.web.outng.orchestrator;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileApp{
	public static void main(final String... args) {
		final SpringApplication app = new SpringApplication(ProfileApp.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", (Object)"8086"));
		app.run(args);
	}
}
