package com.rubypaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}

	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST}, value="/getBoardList")
	public String getBoardList(@ModelAttribute("member")Member member,Model model) {
		
		if(member.getId() == null)
			return "redirect:login";
		model.addAttribute(boardService.getBoardList());
		return "getBoardList";
	}	
	
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member")Member member) {
		if(member.getId() == null)
			return "redirect:login";
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member")Member member, Board board) {
		if(member.getId() == null)
			return "redirect:login";
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member")Member member,Board board,Model model) {
		if(member.getId() == null)
			return "redirect:login";
		model.addAttribute("board",boardService.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member")Member member,Board board) {
		if(member.getId() == null)
			return "redirect:login";
		boardService.updateBoard(board);
		return "forward:getBoardList";  //redirect로 바꿔도 됨
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member")Member member,Board board) {
		if(member.getId() == null)
			return "redirect:login";
		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}
	
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("greeting","Hello 타임리프");
	}
	
}
