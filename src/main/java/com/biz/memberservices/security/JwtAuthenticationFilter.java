package com.biz.memberservices.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtDecoder jwtDecoder;
	
	private final JwtToPrincipalConverter jwtToPrincipalConverter;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		extractTokenFromRequest(request) //accepts token
			.map(jwtDecoder::decode) //decodes token. this is a method reference, simplification of lambda exp
			.map(jwtToPrincipalConverter::convert) // converts the token
			.map(UserPrincipalAuthenticationToken::new) // creates new UserPrincipalAuthenticationToken
			.ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication)); 
		
		
		filterChain.doFilter(request, response);
		
	}

	private Optional<String> extractTokenFromRequest(HttpServletRequest request){
		
		var token = request.getHeader("Authorization");
		if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return Optional.of(token.substring(7));
		}
		return Optional.empty();
	}
}
