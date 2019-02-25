package com.dapp.outng.common.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(name = "props", value = "classpath:core-urls-${SPRING_PROFILES_ACTIVE}.properties", ignoreResourceNotFound = false)
@ConfigurationProperties(prefix = "request")
public class RestConfig {

	@Value("${request.timeout}")
	private int timeout;

	public int getTimeout() {
		return timeout;
	}
}
