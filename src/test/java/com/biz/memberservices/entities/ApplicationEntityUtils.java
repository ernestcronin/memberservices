package com.biz.memberservices.entities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationEntityUtils {

	public static ApplicationEntity buildApplicationEntity() {

		return ApplicationEntity.builder().id(5L).name("TEST_APP").password("testsecret").build();
	}
}
