package com.dapp.web.outng.orchestrator.configs;

import com.dapp.outng.common.http.UrlConstants;
import com.dapp.outng.common.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(JwtSecurityConfigurerAdapter.ORDER)
    public class JwtSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        static final int ORDER = 1;

        @Autowired
        private JwtTokenFilter jwtTokenFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.headers().xssProtection().and().httpStrictTransportSecurity().and().contentTypeOptions();
            http.requestMatchers().antMatchers(UrlConstants.SERVICES_CONTEXT_PATH);
            http.authorizeRequests().anyRequest().authenticated();
            http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
}
