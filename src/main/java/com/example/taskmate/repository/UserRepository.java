package com.example.taskmate.repository;

import com.example.taskmate.entity.User;

public interface UserRepository {

	void insert(User user);
	
	User selectByUserId(String userId);

}
