package com.biz.memberservices.dtos;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {

	private Long pid;
	
	private String memberIdentifier;
	
	private String memberFirstName;
	
	private String memberLastName;
	
	private Instant birthDate;
}
