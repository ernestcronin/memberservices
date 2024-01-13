package com.biz.memberservices.dtos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
	
	@JsonProperty(value = "KEY")
	private Long pid;
	
	@JsonProperty(value = "MEMBER_IDENTIFIER")
	private String memberIdentifier;
	
	@JsonProperty(value = "FIRST_NAME")
	private String memberFirstName;
	
	@JsonProperty(value = "LAST_NAME")
	private String memberLastName;
	
	@JsonProperty(value = "BIRTH_DATE")
	private Instant birthDate;
}
