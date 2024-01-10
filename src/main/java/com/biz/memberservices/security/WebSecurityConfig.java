package com.biz.memberservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	//make spring aware of filter
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final CustomUserDetailService customUserDetailService;
	
	
	@Bean
	public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.cors(AbstractHttpConfigurer::disable);
		http.csrf(AbstractHttpConfigurer::disable);
		http.sessionManagement((sessionManagement) -> sessionManagement.sessionConcurrency(
						(sessionConcurrency) -> sessionConcurrency.maximumSessions(1)));  //..expiredUrl("/login?expired")
		http.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.formLogin(AbstractHttpConfigurer::disable);
		http.securityMatcher("/**").authorizeHttpRequests(registery ->
	    	registery.requestMatchers("/api/**", "/h2-console/**")
	    		.permitAll()
	    	//.requestMatchers("/secured").authenticated()
	    	.requestMatchers("/confirmTokenForAdmin/**").hasAnyAuthority("ADMIN"));
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(customUserDetailService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
}
