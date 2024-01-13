package com.biz.memberservices.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biz.memberservices.entities.MemberEntity;
import com.biz.memberservices.entities.MemberEntityUtils;
import com.biz.memberservices.mappers.MemberDTOMapper;
import com.biz.memberservices.repositories.MemberRepository;
import com.biz.memberservices.services.pubsub.PubSubService;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	private MemberService memberService;

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private MemberDTOMapper memberDtoMapper;

	@Mock
	private PubSubService pubSubService;

	@BeforeEach
	void setUp() {

		memberDtoMapper = Mappers.getMapper(MemberDTOMapper.class);
		memberService = new MemberService(memberRepository, memberDtoMapper, pubSubService);
	}
	
	@Test
	void publishMessageToPubSubTest() {
		
		var memberEntity = MemberEntityUtils.buildMemberEntity();
		
		Long pid = memberService.publishMessageToPubSub(memberEntity);
		assertThat(memberEntity).isNotNull();
		assertThat(memberEntity.getMemberPid()).isEqualTo(pid);
	}

	@Test
	void retrieveMemberEntityByMemberPidTest() {
		var memberEntity = MemberEntityUtils.buildMemberEntity();
		Mockito.when(memberRepository.findMemberEntityByMemberPid(1L))
		.thenReturn(Optional.of(memberEntity));
		
		var entity = memberService.retrieveMemberEntityByMemberPid(1L);
		assertThat(entity).isNotNull().satisfies(m -> validateMemberEntity(m));
	}

	private void validateMemberEntity(MemberEntity retrievedEntity) {
		var entity = MemberEntityUtils.buildMemberEntity();
		assertThat(entity.getMemberPid()).isEqualTo(retrievedEntity.getMemberPid());
		assertThat(entity.getMemberId()).isEqualTo(retrievedEntity.getMemberId());
		assertThat(entity.getFirstName()).isEqualTo(retrievedEntity.getFirstName());
		assertThat(entity.getLastName()).isEqualTo(retrievedEntity.getLastName());
		assertThat(entity.getBirthDate()).isEqualTo(retrievedEntity.getBirthDate());
	}
}
