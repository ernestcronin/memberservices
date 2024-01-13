package com.biz.memberservices.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MEMBER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberEntity {

	@Id
	@Column(name = "PID")
	private Long memberPid;
	
	@Column(name = "MEMBER_ID")
	private String memberId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "BIRTH_DATE")
	private Instant birthDate;
}
