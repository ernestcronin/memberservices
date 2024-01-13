package com.biz.memberservices.mappers;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.biz.memberservices.dtos.MemberDTO;
import com.biz.memberservices.entities.MemberEntity;
import com.biz.memberservices.entities.MemberEntityUtils;

class MemberDTOMapperTest {

	
	private MemberDTOMapper memberDTOMapper;
	
	@BeforeEach
	void setUp() {
		
		memberDTOMapper = Mappers.getMapper(MemberDTOMapper.class);
	}
	
	@Test
	void memberEntityShouldReturnMemberDTOTest() {
		
		var entity = MemberEntityUtils.buildMemberEntity();
		
		var dto = memberDTOMapper.toMemberDto(entity);
		
		assertThat(dto).isNotNull().satisfies(d -> validateDTO(entity, dto));
	}
	
	private void validateDTO(MemberEntity entity, MemberDTO dto) {
		
		assertThat(entity.getMemberPid()).isEqualTo(dto.getPid());
		assertThat(entity.getMemberId()).isEqualTo(dto.getMemberIdentifier());
		assertThat(entity.getFirstName()).isEqualTo(dto.getMemberFirstName());
		assertThat(entity.getLastName()).isEqualTo(dto.getMemberLastName());
		assertThat(entity.getBirthDate()).isEqualTo(dto.getBirthDate());
	}
}
