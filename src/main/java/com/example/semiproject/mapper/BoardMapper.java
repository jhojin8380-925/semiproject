package com.example.semiproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.semiproject.dto.BoardDTO;

@Mapper
public interface BoardMapper {
	
//	[게시글 목록] 한 페이지 분(10개씩) 게시글 목록 반환
//	offset - SQL 조회 시작 위치(1페이지 : 0, 2페이지: 10, 3페이지: 20, ...)
//	size - 한 페이지에 표시할 게시글 수 
	List<BoardDTO> selectAll(@Param("offset") int offset,
							@Param("size") int size);
	
//	[전체 게시글 수] 페이징 결과 계산에 사용
	int selectCount();
	
//	[게시글 작성]
	void insertBoard(BoardDTO boardDTO);
	
}
