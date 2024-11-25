package com.rubypaper.service;

import java.util.List;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberDTO;

public class MemberService {
	
	MemberDAO dao = new MemberDAO();
	
	public List<MemberDTO> getMember(){
		return dao.getMember();
	}
	
	public MemberDTO getMemberById(Integer id) {
		if(dao.getMemberById(id) != null)
			return dao.getMemberById(id);
		else {
			System.out.println("id가 없습니다.");
			return null;
		}
	}
}
