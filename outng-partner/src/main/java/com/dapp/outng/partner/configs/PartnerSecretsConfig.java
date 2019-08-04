package com.dapp.outng.partner.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "partner")
@Getter
public class PartnerSecretsConfig {
	
	private String fbAppAccessToken;

}
