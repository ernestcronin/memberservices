package com.biz.memberservices.pubsub;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biz.memberservices.configurations.ApplicationConfiguration;
import com.biz.memberservices.configurations.DatasourceConfig;
import com.biz.memberservices.configurations.H2DatasourceConfig;
import com.biz.memberservices.dtos.MemberDTOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class MemberDTOJsonSerializationTest {

	 private ObjectMapper objectMapper;
	 
	 @Mock
	 private DatasourceConfig datasourceConfig;

	    @BeforeEach
	    void setUp() {    	
	    	datasourceConfig = new H2DatasourceConfig();
	    	ApplicationConfiguration jacksonConfig = new ApplicationConfiguration(null);
	        objectMapper = jacksonConfig.objectMapper();
	    }
	    
	    @Test
	    void shouldProduceExpectedJson() throws IOException {
	        //given
	        var dto = MemberDTOUtils.buildMemberDTO();
	        var expectedJson = objectMapper.readTree(new File(
	                "src/test/resources/json/MemberEvent.json"));

	        //then
	        var json = objectMapper.readTree(objectMapper.writeValueAsString(dto));
	        assertEquals(expectedJson, json);
	    }
}
