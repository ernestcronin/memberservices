package com.biz.memberservices.configurations;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("h2")
public class H2DatasourceConfig implements DatasourceConfig{
	
	@Override
	public void setup() {
		log.info("Setting up datasource for H2 environment.");			
	}

}
