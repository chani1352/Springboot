package com.rubypaper.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.log.Log;
import com.rubypaper.persistence.LogRepository;
import com.rubypaper.persistence.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private LogRepository logRepo;
	
	public List<Member> getMembers(){
		List<Member> list = memberRepo.findAll();
		logRepo.save(Log.builder()
					.method("get")
					.regidate(new Date())
					.success(list == null ? -1 : 1)
					.build());
		return list;
	}
	
	public Optional<Member> getMemberById(Integer id){
		Optional<Member> mem =memberRepo.findById(id);
		logRepo.save(Log.builder()
					.method("get")
					.regidate(new Date())
					.success((mem.orElse(null) == null) ? 0 : 1)
					.build());
		return mem;

	}
	
	public Member addMember(Member member) {
		logRepo.save(Log.builder()
				.method("post")
				.regidate(new Date())
				.success((member == null) ? 0 : 1)
				.build());
		return memberRepo.save(member);
		
	}
	
	public  Member updateMember(Member member) {
		try {
			Member m = memberRepo.findById(member.getId()).get();
			if(member.getName() != null) m.setName(member.getName());
			if(member.getPass() != null) m.setPass(member.getPass());
			return memberRepo.save(m);
		} catch(Exception e) {
			return null;
		}
	}
	
	public Member removeMember(Integer id) {
		Member m = memberRepo.findById(id).get();
		memberRepo.deleteById(id);
		return m;		
	}
	
	
	
	

}
