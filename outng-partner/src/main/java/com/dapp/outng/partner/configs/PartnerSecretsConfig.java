package com.dapp.outng.partner.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "partner")
@Getter
@Setter
public class PartnerSecretsConfig {
	
	private String fbAppAccessToken;

}
