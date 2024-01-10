package com.biz.memberservices.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.memberservices.entities.UserEntity;
import com.biz.memberservices.repositories.UserEntityRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class UserService {

	public final UserEntityRepository userEntityRepository;

	public Optional<UserEntity> findUserEntityByEmail(String email) {
		return userEntityRepository.findUserByEmail(email);
	}
}
