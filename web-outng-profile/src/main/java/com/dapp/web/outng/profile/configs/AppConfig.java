package com.dapp.web.outng.profile.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.dapp.outng.partner.fb.client.FBClientConfiguration;

@Configuration
@Import(FBClientConfiguration.class)
public class AppConfig {

}
