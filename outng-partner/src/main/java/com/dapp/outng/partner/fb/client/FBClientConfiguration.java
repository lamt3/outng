package com.dapp.outng.partner.fb.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.dapp.outng.common.utils.RestUtils;

@Configuration
public class FBClientConfiguration {
	@Bean
	public FBClient fbClient() {
		return new FBClient();
	}
	
	@Bean(name="fbRestTemplate")
	public RestTemplate restTemplate() {
		return RestUtils.createRestTemplate(5000);
	}
}
