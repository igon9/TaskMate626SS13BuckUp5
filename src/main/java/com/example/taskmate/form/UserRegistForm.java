package com.example.taskmate.form;

import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class UserRegistForm {

	@Size(min=1, max=16, message="1文字から16文字で指定してください。")
	private String userId;
	
	@Size(min=1, message="入力してください。")
	private String password;
	
	@Size(min=1, max=32, message="1文字から32文字で指定してください。")
	private String nickname;
	
	@Size(min=1, message="入力してください。")
	private String role;

}
