package com.example.taskmate.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.taskmate.entity.User;

public class UserDetailsImpl implements UserDetails {

	private final String username;
	private final String password;
	private final String nickname;
	private final Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		username = user.getUserId();
		password = user.getPassword();
		nickname = user.getNickname();
		authorities = AuthorityUtils.createAuthorityList(user.getRole());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getNickname() {
		return nickname;
	}

}
