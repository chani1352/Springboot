package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.MemberVO;

@RestController
public class MemberController {

	private List<MemberVO> members = new ArrayList<>();


	public MemberController() {
		for (int i = 1; i <= 10; i++) {
			MemberVO member = new MemberVO();
			member.setId(i);
			members.add(member);
		}
	}
	

	@GetMapping("/Members")
	public List<MemberVO> getMember() {
		return members;

	}

	@GetMapping("/Member")
	public MemberVO getMemberById(Integer id) {
		for (MemberVO m : members) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	@PostMapping("/member")
	public MemberVO addMember(@RequestBody MemberVO memberVO) {
		if(getMemberById(memberVO.getId()) != null) {
			System.out.println(memberVO.getId() + "가 이미 존재합니다.");
			return null;
		}
		memberVO.setRegidate(new Date());
		members.add(memberVO);
		return memberVO;
	}
	
	@PutMapping("/member")
	public int updateMember(@RequestBody MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if(m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	@DeleteMapping("/member")
	public int removeMember(@RequestBody Integer id) {
		try {
			members.remove(getMemberById(id));
		} catch(Exception e) {
			return 0;
		}
		return 1;
	}
}
