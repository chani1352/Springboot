package com.rubypaper;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@DisplayName("입력 테스트")
	@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
//		board.setWriter("테스터");
		board.setContent("잘 등록되나요?");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		boardRepo.save(board);
	}
	
	@DisplayName("조회 테스트")
//	@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}
	
	@DisplayName("수정 테스트")
//	@Test
	public void testUpdateBoard() {
		System.out.println("==1번 게시글 조회==");
		Board board = boardRepo.findById(1L).get();
		System.out.println("==게시글 제목 수정==");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	}
	
	@DisplayName("삭제 테스트")
//	@Test
	public void testDeleteBoard() {
		boardRepo.deleteById(1L);
	}
	
}
