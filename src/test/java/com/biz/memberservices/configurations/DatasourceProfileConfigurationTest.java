package com.biz.memberservices.configurations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;



@ExtendWith(MockitoExtension.class)
class DatasourceProfileConfigurationTest {
	
	@Mock
	private Environment environment;
	
	@BeforeEach
	void setUp() {
		environment = mock(Environment.class);
		String [] profiles = {"dev" , "h2"};
		when(environment.getActiveProfiles())
        .thenReturn(profiles);   
	}

	@Test
	public void testActiveProfiles() {
        for (String profileName : environment.getActiveProfiles()) {
            System.out.println("Currently active profile - " + profileName);
        }  
    }
}
