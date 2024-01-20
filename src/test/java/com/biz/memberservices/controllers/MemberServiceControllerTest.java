package com.biz.memberservices.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.biz.memberservices.entities.MemberEntityUtils;
import com.biz.memberservices.services.MemberService;

@ExtendWith(MockitoExtension.class)
class MemberServiceControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	MemberServiceController memberServiceController;
	
	@Mock
	MemberService memberService;
	
	 @BeforeEach
	    void setUp() {

	        mockMvc = MockMvcBuilders.standaloneSetup(memberServiceController)
	                .build();
	        
	        var memberEntity = MemberEntityUtils.buildMemberEntity();
			var pid = memberEntity.getMemberPid();
			
			when(memberService.retrieveMemberEntityByMemberPid(pid)).thenReturn(memberEntity);
			when(memberService.publishMessageToPubSub(memberEntity)).thenReturn(pid);
	    }
	 
		@Test
		@WithMockUser
		void shouldReturnMemberTest() throws Exception {

			var entity = memberService.retrieveMemberEntityByMemberPid(1L);
			var resultPid = memberService.publishMessageToPubSub(entity);

			ResponseEntity<String> response = new ResponseEntity<>("Sent message for member with pid: " + resultPid,
					HttpStatus.CREATED);
			
			assertThat(response.getBody()).isNotNull();
			System.out.println(response.getBody().toString());

			mockMvc.perform(post("/api/member/publishMemberEvent").param("pid", "5"))
					.andExpectAll(status().isCreated(), content().encoding("ISO-8859-1"))
					.andDo(MockMvcResultHandlers.print());
			;
		}
}
