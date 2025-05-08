package com.viper.gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {
	
	private static final String[] PUBLIC_URLS = {
		    "/auth-service/login",
		    "/auth-service/register"
		};
	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity security) throws Exception {
		
		return security
				.csrf(customizer -> customizer.disable())
				.authorizeExchange(cutomizer -> cutomizer
						.pathMatchers(PUBLIC_URLS).permitAll()
						.anyExchange().authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
	
	

}
