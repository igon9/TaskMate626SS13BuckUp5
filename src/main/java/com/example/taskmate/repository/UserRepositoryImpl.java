package com.example.taskmate.repository;

import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.taskmate.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void insert(User user) {

		String sql =
				" INSERT INTO m_user " +
				" (user_id, password, nickname, role) " +
				" VALUES (?, ?, ?, ?) ";	

			jdbcTemplate.update(sql, user.getUserId(),
									 user.getPassword(),
									 user.getNickname(),
									 user.getRole()			);
		
	}

	@Override
	public User selectByUserId(String userId) {
		
		String sql =
				" SELECT        " +
				"   user_id,    " +
				"   password,   " +
				"   nickname,   " +
				"   role        " +
				" FROM          " +
				"   m_user      " +
				" WHERE         " +
				"   user_id = ? ";
				
		User user;
		
		try {

			Map<String, Object> one 
				= jdbcTemplate.queryForMap(sql, userId);

			user = new User();
			user.setUserId((String)one.get("user_id"));
			user.setPassword((String)one.get("password"));
			user.setNickname((String)one.get("nickname"));
			user.setRole((String)one.get("role"));

		} catch (EmptyResultDataAccessException e) {
			user = null;
		}

		return user;

	}

}
