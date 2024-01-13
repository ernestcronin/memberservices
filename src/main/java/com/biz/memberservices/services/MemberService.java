package com.biz.memberservices.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.memberservices.dtos.MemberDTO;
import com.biz.memberservices.entities.MemberEntity;
import com.biz.memberservices.mappers.MemberDTOMapper;
import com.biz.memberservices.repositories.MemberRepository;
import com.biz.memberservices.services.pubsub.PubSubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConversionException;
import com.google.pubsub.v1.PubsubMessage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	private final MemberDTOMapper memberDtoMapper;

	private final PubSubService pubSubService;
	
	private ObjectMapper mapper = new ObjectMapper();

	public Long retrieveMemberEntityByMemberPid(Long pid) {

		log.info("Retrieve member id with pid " + pid);
		Optional<MemberEntity> entity = getMemberRepository().findMemberEntityByMemberPid(pid);

		if (entity.isEmpty()) {
			log.info("no member found for pid: " + pid);
			return null;
		}

		MemberDTO dto = memberDtoMapper.toMemberDto(entity.get());
		pubSubService.publishToPubSub(dto);
		return dto.getPid();
	}

	public List<MemberDTO> pullMessagesFromPubsub() {
		List<PubsubMessage> messages = pubSubService.pullFromPubSub();
		List<MemberDTO> memberDTOs = new ArrayList<>();
		try {
			for (PubsubMessage msg : messages) {
				
				MemberDTO dto = this.mapper.readerFor(MemberDTO.class).readValue(msg.getData().toByteArray());
				memberDTOs.add(dto);
			}
			
		} catch (IOException ex) {
			throw new PubSubMessageConversionException(
					"JSON deserialization of an object of type " + MemberDTO.class + " failed.", ex);
		}
		return memberDTOs;
	}
}
