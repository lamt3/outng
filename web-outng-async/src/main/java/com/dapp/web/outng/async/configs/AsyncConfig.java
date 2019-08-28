package com.dapp.web.outng.async.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dapp.outng.common.factories.ActionHandlerFactory;

@Configuration
@ComponentScan({"com.dapp.outng"})
public class AsyncConfig {

//	@Bean
//	public ConsumerInitializer consumerInitializer() {
//		return new ConsumerInitializer();
//	}
	
	@Bean
	public ActionHandlerFactory actionHandlerFactory() {
		return new ActionHandlerFactory();
	}
	
	
}
