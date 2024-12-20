package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rubypaper.domain.Board;

@Controller
public class BoardController {
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value="/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = new ArrayList<Board>();
		
		for(long i = 1L ; i <= 10L ; i++) {
			boardList.add(Board.builder()
						.seq(i)
						.title("게시판 프로그램 테스트")
						.writer("도우너")
						.content("게시판 프로그램 테스트입니다.")
						.build());
		}
		model.addAttribute("boardList",boardList);
		return "getBoardList";		
	}
}
