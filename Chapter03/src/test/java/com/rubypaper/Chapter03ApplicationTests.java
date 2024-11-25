package com.rubypaper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Chapter03ApplicationTests {
	
	@DisplayName("최초 JUnit 테스트 메서드 실행")
	@Test
	void contextLoads() {
		System.out.println("contextLoads 실행");
	}

}
