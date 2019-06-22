package com.dapp.web.outng.profile.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.dapp.outng.partner.fb.client.FBClientConfiguration;
import com.dapp.web.outng.profile.factories.UserValidatorFactory;

@Configuration
@Import(FBClientConfiguration.class)
public class AppConfig {
	
	@Bean
	public UserValidatorFactory userValidatorFactory() {
		return new UserValidatorFactory();
	}

}
