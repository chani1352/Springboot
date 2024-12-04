package com.rubypaper.service;

import java.util.Date;
import java.util.List;

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
					.success(list == null ? 0 : 1)
					.build());
		return list;
	}
	
	public Member getMemberById(Integer id){
		Member mem =memberRepo.findById(id).orElse(null);
		logRepo.save(Log.builder()
					.method("get")
					.regidate(new Date())
					.success((mem == null) ? 0 : 1)
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
			logRepo.save(Log.builder()
				     .method("put")
				     .regidate(new Date())
<<<<<<< HEAD
				     .success(1)
=======
				     .success((m == null) ? 0 : 1)
>>>>>>> d216e513e7c4a80040a8a74d2ce16e3518573dec
				     .build()); 
			return memberRepo.save(m);
		} catch(Exception e) {
			logRepo.save(Log.builder()
				     .method("put")
				     .regidate(new Date())
				     .success(0)
				     .build()); 
			return null;
		}
	}
	
	public Member removeMember(Integer id) {
		Member m = memberRepo.findById(id).orElse(null);
        	if (m != null) {
           		 memberRepo.deleteById(id);
			logRepo.save(Log.builder()
				     .method("delete")
				     .regidate(new Date())
<<<<<<< HEAD
				     .success(1)
				     .build());
        	} else {
    			logRepo.save(Log.builder()
      				     .method("delete")
      				     .regidate(new Date())
      				     .success(0)
      				     .build());
        	}

   
        	
=======
				     .success((m == null) ? 0 : 1)
				     build());
        	}
>>>>>>> d216e513e7c4a80040a8a74d2ce16e3518573dec
        	return m;		
	}
	

}