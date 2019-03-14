package com.dapp.outng.partner.fb.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FBClientConfiguration {
	@Bean
	public FBClient fbClient() {
		return new FBClient();
	}
	
	@Bean(name="fbRestTemplate")
	public RestTemplate restTemplate() {
	
		
		
		return null;
	}
	

}
