package com.example.semiproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
//	member테이블 1행(row)의 데이터를 담는 클래스
	private int memberId;
	private String memberLoginId;
	private String memberName;
	private String memberEmail;
	private String memberPwd;
	private String memberPhone;
	
	
	
}
