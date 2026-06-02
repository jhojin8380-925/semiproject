package com.example.semiproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 서버 루트 주소(localhost:8810) 에 접속 했을 때 게시판 목록으로 바로 이동
@Controller
public class HomeController {
	
//	아무 경로 없이 주소만 입력 했을 때(localhost:8810)
	@GetMapping("/")
	public String home() {
		return "redirect:/board/list";
	}
}
