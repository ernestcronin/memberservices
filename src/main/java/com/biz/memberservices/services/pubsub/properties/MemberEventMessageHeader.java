package com.biz.memberservices.services.pubsub.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.threeten.bp.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class MemberEventMessageHeader {

	@Value("${com.biz.memberservice.pubsub.topic}")
	public String topic;
	
	@Value("${com.biz.memberservice.pubsub.subscription}")
	public String subscription;
	
	public Map<String, String> headers;
	{
		headers =  new HashMap<>();
		headers.put("eventType", "Member-Event");
		headers.put("timestamp", Instant.now().toString());
		headers.put("topic-name", topic);
		headers.put("subscription", subscription);
	}

}
