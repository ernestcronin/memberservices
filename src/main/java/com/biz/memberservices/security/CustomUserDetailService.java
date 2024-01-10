package com.biz.memberservices.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.biz.memberservices.entities.UserEntity;
import com.biz.memberservices.services.UserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class CustomUserDetailService implements UserDetailsService{


	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userService.findUserEntityByEmail(username).orElseThrow();
		return UserPrincipal.builder()
				.userId(user.getId())
				.email(user.getEmail())
				.authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
				.password(user.getPassword())
				.build();
	}

}
