package com.dapp.web.outng.orchestrator;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OrchestratorApp{
	public static void main(final String... args) {
		final SpringApplication app = new SpringApplication(OrchestratorApp.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", (Object)"8086"));
		app.run(args);
	}
}
