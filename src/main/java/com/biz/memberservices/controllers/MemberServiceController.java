package com.biz.memberservices.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz.memberservices.dtos.MemberDTO;
import com.biz.memberservices.security.UserPrincipal;
import com.biz.memberservices.services.MemberService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberServiceController {

	private final MemberService memberService;

	@GetMapping(value = "/retrieveMemberEvent")
	public ResponseEntity<List<MemberDTO>> getMessagesFromPubSub(@AuthenticationPrincipal UserPrincipal principal) {
		List<MemberDTO> dto = memberService.pullMessagesFromPubsub();
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/publishMemberEvent")
	public ResponseEntity<String> publishMemberEvent(@RequestParam Long pid,
			@AuthenticationPrincipal UserPrincipal principal) {

		var entity = getMemberService().retrieveMemberEntityByMemberPid(pid);
		var resultPid = getMemberService().publishMessageToPubSub(entity);

		ResponseEntity<String> response;
		if (resultPid == null) {
			response = new ResponseEntity<>("No member found with pid: " + pid, HttpStatus.NOT_FOUND);
			return response;
		}
		response = new ResponseEntity<>("Sent message for member with pid: " + pid, HttpStatus.CREATED);
		return response;
	}
}
