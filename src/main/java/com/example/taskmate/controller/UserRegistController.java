package com.example.taskmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.taskmate.entity.User;
import com.example.taskmate.form.UserRegistForm;
import com.example.taskmate.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserRegistController {

	private final UserService service;
	
	/*--- ユーザ登録画面表示 ---*/
	@GetMapping("/admin/show-user-regist-form")
	public String showUserRegistForm(@ModelAttribute UserRegistForm form) {
		return "admin/user-regist";
	}

	/*--- ユーザ登録リクエスト（登録画面より） ---*/
	@PostMapping("/admin/regist-user")
	public String registUser(
			@Validated @ModelAttribute UserRegistForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return "admin/user-regist";
		}
		
		return "admin/user-confirm-regist";
	}

	/*--- ユーザ登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/admin/confirm-regist-user")
	public String confirmRegistUser(
			@Validated UserRegistForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return "admin/user-regist";
		}

		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setNickname(form.getNickname());
		user.setRole(form.getRole());

		service.regist(user);
		
		redirectAttributes.addFlashAttribute("msg", "(ユーザ登録)");
		
		return "redirect:/admin/complete";
	}

    /*--- 登録完了画面表示 ---*/
    @GetMapping("/admin/complete")
    public String showComplete() {
        return "admin/complete";  // 完了画面 (complete.html)
    }
}
