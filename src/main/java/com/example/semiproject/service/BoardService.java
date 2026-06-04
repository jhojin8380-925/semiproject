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
//	keyword가 빈 문자열이면 전체 목록, 내용이 있으면 검색 결과 반환 
	public List<BoardDTO> getBoardList(int offset, int size, String keyword) {
		return boardMapper.selectAll(offset, size, keyword);
	}
//	[전체 게시글 수]
	public int getBoardCount(String keyword) {
		return boardMapper.selectCount(keyword);
	}
	
//	[게시글 상세 조회]
	public BoardDTO getBoardId(int boardId) {
		return boardMapper.selectById(boardId);
	}
	
//	[게시글 조회수 증가] BoardController 의 /board/detail 에서 호출
	public void incrementHit(int boardId) {
		boardMapper.updateHit(boardId);
	}
	
//	[게시글 등록]
	public void insertBoard(BoardDTO boardDTO) {
		boardMapper.insertBoard(boardDTO);
	}
	
//	[게시글 수정]
	public void updateBoard(BoardDTO boardDTO) {
		boardMapper.updateBoard(boardDTO);
	}
	
//	[게시글 삭제]
	public void deleteBoard(int boardId) {
		boardMapper.deleteBoard(boardId);
	}
	
	
	
	
	
	
}
