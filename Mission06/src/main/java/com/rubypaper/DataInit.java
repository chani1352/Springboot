package com.rubypaper;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@Component
public class DataInit implements ApplicationRunner {
	
	@Autowired
	private MemberRepository memberRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(int i = 1 ; i <= 100 ; i++) {
			memberRepo.save(Member.builder()
							.pass("pass" + i)
							.name("name" + i)
							.regiDate(new Date())
							.build());
		}

	}

}