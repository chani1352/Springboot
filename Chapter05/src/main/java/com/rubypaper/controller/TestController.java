package com.rubypaper.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {
	
	private final BoardRepository boardRepo;
	
	@GetMapping("/board")
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		try {
			return boardRepo.findById(seq).get();
		}catch(Exception e) {
			return null;
		}
		
	}
	
	@PostMapping("/board")
	public Board postBoard(@RequestBody Board board) {
		return boardRepo.save(board);
	}
	
	@PutMapping("/board")
	public Board putBoard(@RequestBody Board board) {
		try {
			Board putBo = boardRepo.findById(board.getSeq()).get();
			if(board.getContent() != null)
				putBo.setContent(board.getContent());
			if(board.getTitle() != null)
				putBo.setTitle(board.getTitle());
//			if(board.getWriter() != null)
//				putBo.setWriter(board.getWriter());
			return boardRepo.save(putBo);
		} catch(Exception e) {
			return null;
		}
		
	}
	
	@DeleteMapping("/board/{seq}")
	public Board deleteBoard(@PathVariable Long seq) {
		Board putBo = boardRepo.findById(seq).get();
		boardRepo.deleteById(seq);
		return putBo;
	}

}
