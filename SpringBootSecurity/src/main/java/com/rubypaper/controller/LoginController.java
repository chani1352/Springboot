package com.rubypaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private  PasswordEncoder encoder;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("accessDenied");
	}
	
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user){
		if(user == null) {
			return ResponseEntity.ok("로그인 상태가 아닙니다.");
		}
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/join")
	public void join() {}
	
	@PostMapping("/join")
	public String joinProc(Member member) {
		member.setRole(Role.ROLE_MEMBER);
		member.setPassword(encoder.encode(member.getPassword()));
		memberService.save(member);
		return "welcome";
	}
	
	
}
