package com.example.semiproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.semiproject.dto.CommentDTO;

@Mapper
public interface CommentMapper {

//   [댓글 목록] 특정 게시글의 댓글을 조회
//   -> 상세보기 화면 첫 로딩시 필요
   List<CommentDTO> selectByBoardId(@Param("boardId") int boardId);
   
//   [댓글 등록] 새 댓글 추가
//   -> AJAX 등록 요청시 사용
   void insertComment(CommentDTO commentDTO);
   
//   [댓글 삭제] 댓글 번호(commentId)에 해당하는 행 삭제
//   -> AJAX 삭제 요청 시 사용
   void deleteComment(@Param("commentId") int commentId);
   
   
}










