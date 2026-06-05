package com.example.semiproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.semiproject.dto.CommentDTO;
import com.example.semiproject.mapper.CommentMapper;

@Service
public class CommentService {
   
   @Autowired
   private CommentMapper commentMapper;
   
//   [댓글 목록] 특정 게시글의 댓글 목록 반환
   public List<CommentDTO> getCommentsByBoardId(int boardId){
      return commentMapper.selectByBoardId(boardId);
   }
   
//   [댓글 등록] DTO를 받아 DB에 삽입
   public void insertComment(CommentDTO commentDTO) {
      commentMapper.insertComment(commentDTO);
   }
   
//   [댓글 삭제] 댓글 번호로 삭제
   public void deleteComment(int commentId) {
      commentMapper.deleteComment(commentId);
   }

}










