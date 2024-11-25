package com.rubypaper;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class Testmethod {


	@Test
	@Order(3)
	public void test1() {
		System.out.println("test1");
	}
	

	@Test
	@Order(2)
	public void test2() {
		System.out.println("test2");
	}
	

	@Test
	@Order(4)
	public void test3() {
		System.out.println("test3");
	}
	
	@Test
	@Order(1)
	public void test4() {
		System.out.println("test4");
	}
	
}
