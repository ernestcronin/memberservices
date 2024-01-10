package com.biz.memberservices.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biz.memberservices.model.LoginRequest;
import com.biz.memberservices.model.LoginResponse;
import com.biz.memberservices.security.UserPrincipal;
import com.biz.memberservices.services.AuthorizationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authorization")
public class AuthorizationController {

	private final AuthorizationService authorizationService;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {

		return authorizationService.attemptLogin(request.getEmail(), request.getPassword());
	}
	
	
	@GetMapping("/confirmToken")
	public String secured(@AuthenticationPrincipal UserPrincipal principal) {
		return "Logged in successfully for email " + principal.getEmail()
		+ " UserID: " + principal.getUserId();
	}
	
	@GetMapping("/confirmTokenForAdmin")
	public String securedAdmin(@AuthenticationPrincipal UserPrincipal principal) {
		return "Logged in successfully for email " + principal.getEmail()
		+ " UserID: " + principal.getUserId();
	}
}
