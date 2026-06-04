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
//	keyword - 검색어(빈 문자열 이면 전체 게시글 목록 반환)
	List<BoardDTO> selectAll(@Param("offset") int offset,
							@Param("size") int size,
							@Param("keyword") String keyword);
	
//	[전체 게시글 수] 페이징 결과 계산에 사용
//	keyword - 검색어(빈 문자열이면 전체 게시글 수 반환) 
	int selectCount(@Param("keyword") String keyword);
	
//	[게시글 상세 조회] 게시글 번호 1개 조회
	BoardDTO selectById(@Param("boardId") int boardId);
	
//	[게시글 조회수 증가] 상세보기 화면을 열때 실행
	void updateHit(@Param("boardId") int boardId);
	
//	[게시글 작성]
	void insertBoard(BoardDTO boardDTO);

//	[게시글 수정]
	void updateBoard(BoardDTO boardDTO);

//	[게시글 삭제]
	void deleteBoard(@Param("boardId")int boardId);
	
}
