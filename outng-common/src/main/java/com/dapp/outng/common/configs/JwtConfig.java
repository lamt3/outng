package com.dapp.outng.common.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
//@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jwt-configs")
@Getter
@Setter
public class JwtConfig {
	
    private String jwtSecret;
    private Long jwtExpirationInMs;
}
