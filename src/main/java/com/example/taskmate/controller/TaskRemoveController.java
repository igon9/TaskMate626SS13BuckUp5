package com.example.taskmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.taskmate.entity.TaskDetail;
import com.example.taskmate.form.TaskRemoveForm;
import com.example.taskmate.service.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskRemoveController {

	private final TaskService taskService;

	/*--- タスク削除リクエスト（タスク詳細画面より） ---*/
	@PostMapping("/task-remove")
	public String remove(
			@ModelAttribute TaskRemoveForm form,
			Model model) {

		// 削除前のタスク詳細の取得
		TaskDetail taskDetail
			= taskService.findDetailByTaskId(form.getTaskId());
	
		model.addAttribute("taskDetail", taskDetail);

		// タスク削除確認画面に遷移する
		return "/admin/task-confirm-remove";
	}

	/*--- タスク削除リクエスト（削除確認画面より） ---*/
	@PostMapping("/task-confirm-remove")
	public String confirmRemove(
			@ModelAttribute TaskRemoveForm form,
			RedirectAttributes redirectAttributes) {

		// 削除処理
		taskService.remove(form.getTaskId());

		// フラッシュスコープに完了メッセージを表示して リダイレクト
		redirectAttributes.addFlashAttribute("msg", "(タスク削除)");
		
		return "redirect:/task-complete";
	}

}
