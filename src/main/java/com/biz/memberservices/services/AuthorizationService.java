package com.biz.memberservices.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.biz.memberservices.model.LoginResponse;
import com.biz.memberservices.security.JwtIssuer;
import com.biz.memberservices.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

	private final JwtIssuer issuer;

	private final AuthenticationManager authenticationManager;
	
	public LoginResponse attemptLogin(String email, String password) {
		var authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		var principal = (UserPrincipal) authentication.getPrincipal();
		var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.toList();
		
		var token = issuer.issue(principal.getUserId(), principal.getEmail(), roles);
		return LoginResponse.builder().accessToken(token).build();
		
	}
}
