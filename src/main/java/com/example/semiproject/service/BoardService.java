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
	
//	[게시글 목록] offset, size 해당 페이지의 게시글 목록 반환
	public List<BoardDTO> getBoardList(int offset, int size) {
		return boardMapper.selectAll(offset, size);
	}
//	[전체 게시글 수]
	public int getBoardCount() {
		return boardMapper.selectCount();
	}
	
//	[게시글 등록]
	public void insertBoard(BoardDTO boardDTO) {
		boardMapper.insertBoard(boardDTO);
	}
	
	
	
	
	
	
}
