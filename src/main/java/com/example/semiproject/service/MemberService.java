package com.example.semiproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.semiproject.dto.MemberDTO;
import com.example.semiproject.mapper.MemberMapper;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
//	[회원가입] 서비스 메서드명은 sql 명령어(insert) 가 아닌
//	기능 의미(join)로 짓는다
//	=> "insertMember" 보다 "join"이 "회원가입" 이라는 의미를 더 잘 표현
	public void join(MemberDTO memberDTO) { 
		memberMapper.insertMember(memberDTO);
	}
	
//	[아이디 중복 확인] 반환값이 true 라면 이미 사용중인 아이디
	public boolean isLoginIdDuplicated(String loginId) {
		return memberMapper.countByLoginId(loginId) > 0;
	}
	
//	[로그인] 일치하는 회원이 있으면 MemberDTO 반환 없으면 null 반환
	public MemberDTO login(String loginId, String pwd) {
		return memberMapper.selectByLoginIdAndPwd(loginId, pwd);
	}
	
}
