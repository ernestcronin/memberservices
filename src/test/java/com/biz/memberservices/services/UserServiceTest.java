package com.biz.memberservices.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biz.memberservices.entities.UserEntity;
import com.biz.memberservices.entities.UserEntityUtils;
import com.biz.memberservices.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	void setUp() {
		
		userService = new UserService(userRepository);	
		Mockito.when(userRepository.findUserByEmail("testUser")).thenReturn(Optional.of(UserEntityUtils.buildUserEntity()));
	}
	
	@Test
	void findUserEntityByEmailTest() {
		
		var entity = userService.findUserEntityByEmail("testUser");
		assertThat(entity.get()).isNotNull().satisfies(u -> validateUser(entity.get()));
	}
	
	private void validateUser(UserEntity retrievedEntity) {
		
		var entity = UserEntityUtils.buildUserEntity();
		assertThat(entity.getId()).isEqualTo(retrievedEntity.getId());
		assertThat(entity.getEmail()).isEqualTo(retrievedEntity.getEmail());
		assertThat(entity.getPassword()).isEqualTo(retrievedEntity.getPassword());
		assertThat(entity.getRole()).isEqualTo(retrievedEntity.getRole());
	}
}
