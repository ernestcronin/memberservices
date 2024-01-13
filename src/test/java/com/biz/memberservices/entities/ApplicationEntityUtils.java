package com.biz.memberservices.entities;

import org.springframework.beans.factory.annotation.Value;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationEntityUtils {
	
	@Value("${com.biz.memberservice.application.name}")
	private String applicationName;

	public static ApplicationEntity buildApplicationEntity() {

		return ApplicationEntity.builder().id(5L).name(applicationName).password("testsecret").build();
	}
}
