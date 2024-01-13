package com.biz.memberservices.entities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEntityUtils {

	public static UserEntity buildUserEntity() {

		return UserEntity.builder().id(1L).email("ernest.cronin@gmail.com")
				.password("password").role("ADMIN").build();
	}
}
