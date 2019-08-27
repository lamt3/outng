package com.dapp.web.outng.async.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dapp.outng.common.security.JwtTokenFilter;
import com.dapp.outng.common.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class AsyncSecurityConfig {

	@Configuration
	@Order(JwtSecurityConfigurerAdapter.ORDER)
	public class JwtSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		static final int ORDER = 1;

		@Autowired 
		private JwtTokenProvider provider;

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/api/v1/auth/**");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
//            http.headers().xssProtection().and().httpStrictTransportSecurity().and().contentTypeOptions();

			http.antMatcher("/api/v1/services/**").authorizeRequests() //
					.anyRequest().authenticated() //
					.and().addFilterBefore(new JwtTokenFilter(provider), UsernamePasswordAuthenticationFilter.class);
			http.csrf().disable();
		}
	}
}