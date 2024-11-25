package com.rubypaper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class QueryAnnotationTest {

	@Autowired
	private BoardRepository boardRepo;

	@Test
	public void testQueryAnnotaionTest1() {
		List<Board> boardList = boardRepo.queryAnnotationTest1("테스트 제목 10");
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

//	@Test
//	public void testQueryAnnotationTest2() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest2("테스트 제목 10");
//		System.out.println("검색 결과");
//		for (Object[] board : boardList) {
//			for (int i = 0; i < board.length; i++) {
//				System.out.print(Arrays.toString(board));
//			}
//			System.out.println();
//		}
//	}

}
