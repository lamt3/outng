package com.dapp.web.outng.orchestrator.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.dapp.outng.common.configs.SearchConfig;
import com.dapp.outng.partner.fb.client.FBClientConfiguration;
import com.dapp.web.outng.orchestrator.factories.UserValidatorFactory;

@Configuration
@ComponentScan("com.dapp.outng")
@Import({FBClientConfiguration.class, SearchConfig.class})
public class AppConfig {
	
	@Bean
	public UserValidatorFactory userValidatorFactory() {
		return new UserValidatorFactory();
	}

}
