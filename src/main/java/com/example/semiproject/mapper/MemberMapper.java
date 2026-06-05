package com.example.semiproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.semiproject.dto.MemberDTO;

@Mapper
public interface MemberMapper {
//	[회원가입] member테이블에 새 행을 추가
	void insertMember(MemberDTO memberDTO);
	
	
//	[아이디 중복 확인] 해당 아이디가 이미 있으면 1반환, 없으면 0반환
//	@Param("loginId") - XML에서 #{loginId}로 참조할 이름을 지정하는
//					어노테이션	
	int countByLoginId(@Param("loginId") String loginId);
	
//	[로그인] 아이디와 비밀번호가 일치하는 회원 조회
//	일치하는 회원이 없으면 null 반환
	MemberDTO selectByLoginIdAndPwd(@Param("loginId") String loginId, @Param("pwd") String pwd);
	
	
}
