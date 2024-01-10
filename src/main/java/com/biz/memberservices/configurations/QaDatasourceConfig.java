package com.biz.memberservices.configurations;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("qa")
public class QaDatasourceConfig implements DatasourceConfig{

	@Override
	public void setup() {
		 log.info("Setting up datasource for QA environment.");	
	}

}
