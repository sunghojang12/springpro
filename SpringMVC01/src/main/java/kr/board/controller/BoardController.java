package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	@Autowired
	private BoardMapper mapper;
	// /boardList.do 요청 옴
	// HandlerMapping(스프링에서 제공) 이 요청을 메서드와 매핑시켜줌
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
		List<Board> list = mapper.getLists();
		model.addAttribute("list", list);
		return "boardList"; // /WEB-INF/views/     viewResolver(스프링에서 제공) -> forward
	}
	@GetMapping("/boardForm.do")
	public String boardForm() {
		
		return "boardForm"; // /WEB-INF/views/     viewResolver(스프링에서 제공) -> forward
	}
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {// title, content, writer => 파라메터 수집(Board)
		
		mapper.boardInsert(vo);
		return "redirect:/boardList.do"; // redirect
	}
	@GetMapping("/boardContent.do")
	public String boardContent(int idx,Model model) { // @RequestParam("idx") 생략 가능
		Board vo=mapper.boardContent(idx);
		model.addAttribute("vo",vo);
		// 조회수 증가
		mapper.boardCount(idx);
		return "boardContent";
	}
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board vo=mapper.boardContent(idx);
		model.addAttribute("vo",vo);
		return "boardUpdate";
	}
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {// idx, title, content
		mapper.boardUpdate(vo); // 수정
		return "redirect:/boardList.do";
	}
}
