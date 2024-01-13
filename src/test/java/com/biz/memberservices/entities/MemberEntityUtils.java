package com.biz.memberservices.entities;

import java.time.Instant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberEntityUtils {

	public static MemberEntity buildMemberEntity() {

		return MemberEntity.builder().memberPid(1L).memberId("12345").firstName("Ernest").lastName("Cronin")
				.birthDate(Instant.parse("1980-05-06T00:00:00Z")).build();
	}
}
