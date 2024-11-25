package com.rubypaper.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.dao.MemberDAO;
import com.rubypaper.domain.MemberDTO;
import com.rubypaper.logdao.LogDAO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao;
	@Autowired
	LogDAO ldao;
	
	public MemberService() {
		System.out.println("MemberService 생성");
	}

	public List<MemberDTO> getMember(){
		Map<String,Object> map = dao.getMember();
		ldao.logData(map);
		return (List<MemberDTO>)map.get("list");
	}
	
	public MemberDTO getMemberById(Integer id) {
		Map<String,Object> map = dao.getMemberById(id);
		ldao.logData(map);
		if(dao.getMemberById(id) == null)
			map.put("result",0);

		return (MemberDTO)map.get("dto");
	}

	
	public int addMember(MemberDTO dto) {
		Map<String,Object> map = dao.addMember(dto);	
		ldao.logData(map);

		return (int)(map.get("result"));
	}
	
	public int updateMember(MemberDTO dto) {
		Map<String,Object> map = dao.updateMember(dto);
		ldao.logData(map);
		return (int)(map.get("result"));
	}
	
	public int removeMember(Integer id) {
		Map<String,Object> map = dao.removeMember(id);
		ldao.logData(map);
		return (int)(map.get("result"));
	}
	
	
	
}
