package com.example.semiproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.semiproject.dto.BoardDTO;
import com.example.semiproject.dto.MemberDTO;
import com.example.semiproject.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	----------------------------------
//	[게시글 목록] GET /board/list

	@GetMapping("/list")
	public String list(Model model) {
		
		List<BoardDTO> boardList = boardService.getBoardList();
		
//		Model.addAttribute("키", 값)
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
//	---------------------------------------------------------
//	[게시글 작성 폼 이동] GET /board/write (로그인 필요)
	
	@GetMapping("/write")
	public String writeForm(HttpSession session) {
		
//		로그인 여부 확인
		if(session.getAttribute("loginMember") == null) {
//			로그인이 안된 상태라면 => 로그인 페이지로 이동
			return "redirect:/member/login";
		}
		
//		로그인이 된 상태라면 폼 화면으로 이동
		return "board/write";
		
	}
	
//	----------------------------------------------------------
//	[게시글 작성 처리] POST /board/write
	
	@PostMapping("/write")
	public String wirte(BoardDTO boardDTO, HttpSession session) {
		MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			return "redirect:/member/login";
		}
//		작성자 번호를 세션에서 꺼내 DTO에 설정
		boardDTO.setMemberId(loginMember.getMemberId());
		boardService.insertBoard(boardDTO);
		
//		글 작성 후 게시글 목록으로 이동
		return "redirect:/board/list";
	}
	
	
}
