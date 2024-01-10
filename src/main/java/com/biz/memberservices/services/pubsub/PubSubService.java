package com.biz.memberservices.services.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.threeten.bp.Instant;

import com.biz.memberservices.dtos.MemberDTO;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.AcknowledgeablePubsubMessage;
import com.google.pubsub.v1.PubsubMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PubSubService {

	private final PubSubTemplate pubSubTemplate;

	@Value("${com.biz.memberservice.pubsub.topic}")
	private String topic;
	
	@Value("${com.biz.memberservice.pubsub.subscription}")
	private String subscription;
	
	private Map<String, String> headers = new HashMap<>();

	public void publishToPubSub(MemberDTO dto) {
		log.info("publishing member entity to pubsub");
		buildHeaders();
		pubSubTemplate.publish(topic, dto, headers);
	}

	public List<PubsubMessage> pullFromPubSub() {
		List<PubsubMessage> messages = new ArrayList<>();
		Collection<AcknowledgeablePubsubMessage> ackMessages = this.pubSubTemplate
				.pull(subscription, 10, true);
		ackMessages.forEach(ack -> messages.add(ack.getPubsubMessage()));
		return messages;
	}
	
	private void buildHeaders() {
		headers.put("eventType", "Member-Event");
		headers.put("timestamp", Instant.now().toString());
		headers.put("topic-name", topic);
		headers.put("subscription", subscription);
	}
}
