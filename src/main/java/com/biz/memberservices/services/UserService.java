package com.biz.memberservices.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.memberservices.entities.UserEntity;
import com.biz.memberservices.repositories.UserRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class UserService {

	public final UserRepository userRepository;

	public Optional<UserEntity> findUserEntityByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}
}
