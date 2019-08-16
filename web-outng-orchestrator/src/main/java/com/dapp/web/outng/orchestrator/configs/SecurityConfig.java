package com.dapp.web.outng.orchestrator.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.dapp.outng.common.http.UrlConstants;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

	@Configuration
	@Order(JwtSecurityConfigurerAdapter.ORDER)
	public class JwtSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		static final int ORDER = 1;

//		@Autowired
//		private JwtTokenFilter jwtTokenFilter;

		private String[] sPath = UrlConstants.SERVICES_CONTEXT_PATH;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
//            http.headers().xssProtection().and().httpStrictTransportSecurity().and().contentTypeOptions();
//            http.requestMatchers().antMatchers(sPath);
//            http.authorizeRequests().anyRequest().authenticated();
//            http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
			http.authorizeRequests().antMatchers("/").permitAll();
			http.csrf().disable();
		}
	}
}
