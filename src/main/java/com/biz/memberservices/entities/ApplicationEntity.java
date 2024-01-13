package com.biz.memberservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "APP_PROPERTIES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationEntity {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@JsonIgnore
	@Column(name = "PASSWORD")
	private String password;
}
