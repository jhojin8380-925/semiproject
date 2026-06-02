package com.example.semiproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.semiproject.dto.BoardDTO;
import com.example.semiproject.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
//	[게시글 목록]
	public List<BoardDTO> getBoardList() {
		return boardMapper.selectAll();
	}
	
//	[게시글 등록]
	public void insertBoard(BoardDTO boardDTO) {
		boardMapper.insertBoard(boardDTO);
	}
	
	
	
	
}
