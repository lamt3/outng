package com.dapp.web.outng.async;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsyncApp {
	public static void main(final String... args) {
		final SpringApplication app = new SpringApplication(AsyncApp.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", (Object)"8088"));
		app.run(args);
//		SpringApplication.run(MatcherApp.class, args);
	}
//	@Bean
//    public MatcherHandler matcherHandler() {
//        return new MatcherHandler();
//    }
// 
//    @Bean
//    public HandlerMapping handlerMapping() {
//        Map<String, WebSocketHandler> map = new HashMap<>();
//        map.put("/matcher", matcherHandler());
// 
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        mapping.setUrlMap(map);
//        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return mapping;
//    }
// 
//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter(webSocketService());
//    }
// 
//    @Bean
//    public WebSocketService webSocketService() {
//        return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
//    }

}
