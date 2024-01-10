package com.biz.memberservices.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class ApplicationConfiguration {

	@Autowired
	DatasourceConfig datasourceConfig;

	@Bean
	@Primary
	public PubSubMessageConverter pubSubMessageConverter(ObjectMapper objectMapper) {
		return new JacksonPubSubMessageConverter(objectMapper);
	}
	
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
	  ObjectMapper mapper = new ObjectMapper();
	  mapper.registerModule(new JavaTimeModule());
	  mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	  mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	  mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	  return mapper;
	}
}
