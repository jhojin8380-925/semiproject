package com.example.semiproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.semiproject.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	
//	[게시글 목록]
	List<BoardDTO> selectAll();
	
//	[게시글 작성]
	void insertBoard(BoardDTO boardDTO);
	
}
