package com.rubypaper.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Member;
import com.rubypaper.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public List<Member> getMemebers(){
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public Member getMemberById(@PathVariable Integer id){
		return memberService.getMemberById(id);
	}
	
	@PostMapping("/member")
	public Member addMember(@RequestBody Member member) {
		return memberService.addMember(member);
	}
	
	@PutMapping("/member")
	public Member updateMember(@RequestBody Member member) {
		return memberService.updateMember(member);
	}
	@DeleteMapping("/member/{id}")
	public Member removeMember(@PathVariable Integer id) {
		return memberService.removeMember(id);
	}
	

}
