package com.rubypaper.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rubypaper.domain.MemberVO;

public class MemberService {
	
	private List<MemberVO> members = new ArrayList<>();
	
	public MemberService() {
		for(int i = 1 ; i <= 10 ; i++) {
			MemberVO member = new MemberVO();
			member.setId(i);
			members.add(member);
		}
	}
	

	public List<MemberVO> getMember(){
		return members;
	}
	
	public MemberVO getMemberById(Integer id) {
		for(MemberVO m : members) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		if(getMemberById(memberVO.getId()) != null) {
			System.out.println(memberVO.getId() + "가 이미 존재합니다.");
			return null;
		}
		memberVO.setRegidate(new Date());
		members.add(memberVO);
		return memberVO;
	}
	
	public int updateMember(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if(m == null)
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
	}
	
	public int removeMember(Integer id) {
		
		
		try {
			members.remove(getMemberById(id));
		} catch(Exception e) {
			return 0;
		}
		return 1;
	}
	
	
	
	

}
