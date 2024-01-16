package com.example.memos.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.memos.dto.AddMemoRequest;
import com.example.memos.dto.AddUserRequest;
import com.example.memos.dto.AddUserResponse;
import com.example.memos.service.MemoService;
import com.example.memos.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebController {
	
	private final UserService userService;
	private final MemoService memoService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String signin(
			@RequestParam(name="message", required = false)String message,
			Model model) {
		model.addAttribute("message", message);
		return "signin";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String postJoin(
			@RequestParam("username")String username,
			@RequestParam("password")String password,
			RedirectAttributes ra) {
		AddUserResponse result = userService.addUser(new AddUserRequest(username, password));
		
		ra.addAttribute("message", "User Added. Please Sign in.");
		return "redirect:/signin";
	}
	
	@GetMapping("/memos")
	public String memos(Model model, Principal user) {
		model.addAttribute("memos", memoService.getMemoByUser(user.getName()));
		return "memos";
	}
	
	@PostMapping("/memos")
	public String addMemo(
			@RequestParam("body") String body,
			Principal user) {
		memoService.addMemo(new AddMemoRequest(body, user.getName()));
		return "redirect:/memos";
	}
	
	@PostMapping("/memos/delete/{id}")
	public String deleteMemo(
			@PathVariable("id") Long id) {
		memoService.deleteMemo(id);
		return "redirect:/memos";
	}
}
