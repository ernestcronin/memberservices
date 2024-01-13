package com.biz.memberservices.dtos;

import java.time.Instant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberDTOUtils {

	public static MemberDTO buildMemberDTO() {

		return MemberDTO.builder().pid(1L).memberIdentifier("12345").memberFirstName("Ernest")
				.memberLastName("Cronin").birthDate(Instant.parse("1980-05-06T00:00:00Z"))
				.build();
	}
}
