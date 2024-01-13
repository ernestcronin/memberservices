package com.biz.memberservices.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import com.biz.memberservices.entities.ApplicationEntityUtils;
import com.biz.memberservices.repositories.ApplicationRepository;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

	
	private ApplicationService applicationService;

	@Mock
	private ApplicationRepository applicationRepository;
	
	@Value("${com.biz.memberservice.application.name}")
	private String applicationName;

	@BeforeEach
	void setUp() {
		
		applicationService = new ApplicationService(applicationRepository);
		
		when(applicationRepository.findApplicationEntityByName(applicationName))
			.thenReturn(Optional.of(ApplicationEntityUtils.buildApplicationEntity()));
	}

	@Test
	void retrieveApplicationPasswordTest() {
		var entity = ApplicationEntityUtils.buildApplicationEntity();
		var password = applicationService.retrieveApplicationPassword();
		assertThat(password).isNotNull().isEqualTo(entity.getPassword());
	}
}
