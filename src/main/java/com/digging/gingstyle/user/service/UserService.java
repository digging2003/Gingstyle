package com.digging.gingstyle.user.service;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.common.MD5HashingEncoder;
import com.digging.gingstyle.user.domain.User;
import com.digging.gingstyle.user.repository.UserRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	// 중복확인 api
	public User duplicateEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	public boolean addUser(String name, String email, String password) {
		
		String encyptPassword = MD5HashingEncoder.encode(password);
		
		User user = User.builder()
				.name(name)
				.email(email)
				.password(encyptPassword)
				.build();
		
		try {
			userRepository.save(user);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
		
	}
	
	public User getUser(String email, String password) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		return userRepository.findByEmailAndPassword(email, encryptPassword);
		
	}
	
}
