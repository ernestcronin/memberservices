package com.biz.memberservices.security;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.biz.memberservices.services.ApplicationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtDecoder {
	
	private final ApplicationService applicationService;

	public DecodedJWT decode(String token) {
		
		String jwt = applicationService.retrieveApplicationPassword();
		
		return JWT.require(Algorithm.HMAC256(jwt))
				.build().verify(token);
	}
}
