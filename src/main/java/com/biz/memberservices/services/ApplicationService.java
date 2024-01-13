package com.biz.memberservices.services;

import org.springframework.stereotype.Service;

import com.biz.memberservices.entities.ApplicationEntity;
import com.biz.memberservices.repositories.ApplicationRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {

	@JsonIgnore
	private static final String APP_NAME = "MEMBER_SERVICES";
	
	private final ApplicationRepository applicationRepository;
	
	public String retrieveApplicationPassword() {
		
		ApplicationEntity entity = applicationRepository.findApplicationEntityByName(APP_NAME).orElseThrow();
		return entity.getPassword();
	}
}
