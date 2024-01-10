package com.biz.memberservices.security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.biz.memberservices.services.ApplicationService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class JwtIssuer {
	
	private final ApplicationService applicationService;
	
	private static final String CLAIM_EMAIL = "email";
	private static final String CLAIM_ROLES = "roles";
	
	public String issue(long userId, String email, List<String> roles) {
		
		String jwt = applicationService.retrieveApplicationPassword();
		
		return JWT.create().withSubject(String.valueOf(userId))
				.withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
				.withClaim(CLAIM_EMAIL, email)
				.withClaim(CLAIM_ROLES, roles)
				.sign(Algorithm.HMAC256(jwt));
	}
}
