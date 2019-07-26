package com.dapp.outng.common.configs;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "jwt-config")
@Getter
public class JwtConfig {

    private String jwtSecret;
    private Long jwtExpirationInMs;
}
