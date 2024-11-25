package com.rubypaper.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.MemberDTO;
import com.rubypaper.service.MemberService;

@RestController
public class MemberController {
	private MemberService service;
	
	public MemberController() {
		service = new MemberService();
	}
	
	@GetMapping("/members")
	public List<MemberDTO> getMember(){
		return service.getMember();
	}
	
	@GetMapping("/member")
	public MemberDTO getMemberById(Integer id) {
		return service.getMemberById(id);
	}

}
