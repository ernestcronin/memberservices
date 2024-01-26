package com.biz.memberservices.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.biz.memberservices.dtos.MemberDTO;
import com.biz.memberservices.entities.MemberEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MemberDTOMapper {

	@Mapping(target = "pid", source = "memberPid")
	@Mapping(target = "memberIdentifier", source = "memberId")
	@Mapping(target = "memberFirstName", source = "firstName")
	@Mapping(target = "memberLastName", source = "lastName")
	@Mapping(target = "birthDate", source = "birthDate")
	MemberDTO toMemberDto(MemberEntity entity);
}
