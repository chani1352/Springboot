package com.rubypaper.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.MemberVO;
import com.rubypaper.service.MemberService;

@RestController
public class MemberController {
	
	private MemberService service;
	
	
	public MemberController() {
		service = new MemberService();
	}
	
	@GetMapping("/Members")
	public List<MemberVO> getMember(){
		return service.getMember();
	}
	
	@GetMapping("/Member")
	public MemberVO getMemberById(Integer id) {
		return service.getMemberById(id);
	}
	
	@PostMapping("/member")
	public MemberVO addMember(@RequestBody MemberVO memberVO) {
		return service.addMember(memberVO);
	}
	
	@PutMapping("/member")
	public int updateMember(@RequestBody MemberVO memberVO) {
		return service.updateMember(memberVO);
	}
	
	@DeleteMapping("/member")
	public int removeMember(@RequestBody Integer id) {
		return service.removeMember(id);
	}
	
	

}
