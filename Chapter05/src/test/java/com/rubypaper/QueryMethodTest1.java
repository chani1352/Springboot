package com.rubypaper;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class QueryMethodTest1 {

	@Autowired
	private BoardRepository boardRepo;
	
//	@BeforeEach
	public void dataPrepare() {
		for(int i = 1 ; i <= 100 ; i++) {
			Random rnd = new Random();
			Long cnt = rnd.nextLong(101);
			Board board = new Board();
			board.setTitle("테스트 제목 " + cnt);
//			board.setWriter("테스터");
			board.setContent("테스트 내용 " + cnt);
			board.setCreateDate(new Date());
			board.setCnt(cnt);
			boardRepo.save(board);
		}
	}
	
//	@Test
//	public void testFindByTitle() {
//		List<Board> boardList = boardRepo.findByTitleContaining("1");
//		System.out.println("검색 결과");
//		for(Board board : boardList) {
//			System.out.println("---> " + board.toString());
//		}
//	}
	
//	@Test
//	public void testFindByTitleAndCntGreaterThan()
//	
	
}
