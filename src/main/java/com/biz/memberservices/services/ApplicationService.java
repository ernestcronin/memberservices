package com.biz.memberservices.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.biz.memberservices.entities.ApplicationEntity;
import com.biz.memberservices.repositories.ApplicationRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {

	@JsonIgnore
	@Value("${com.biz.memberservice.application.name}")
	private String applicationName;
	
	private final ApplicationRepository applicationRepository;
	
	public String retrieveApplicationPassword() {
		
		ApplicationEntity entity = applicationRepository.findApplicationEntityByName(applicationName).orElseThrow();
		return entity.getPassword();
	}
}
