package com.example.semiproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
	
//	Comment 테이블 1개의 행의 데이터를 담는 클래스
	private int commentId; // 댓글 번호
	private String commentContent;  // 댓글 내용
	private String commentDate; // 작성 일시
	private int boardId;  // 게시글 번호
	private int memberId;  // 회원번호
	
//	join으로 가져오는 필드 - 댓글 작성자 아이디 표시용
	private String memberLoginId;
	
	
	
}
