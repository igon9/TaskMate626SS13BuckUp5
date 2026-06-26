package com.example.taskmate.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmate.entity.User;
import com.example.taskmate.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void regist(User user) {

//		String hashed = "{noop}" + user.getPassword();
		String hashed = passwordEncoder.encode(user.getPassword());
		
		// パスワードの再設定
		user.setPassword(hashed);
		
		repository.insert(user);

	}

}
