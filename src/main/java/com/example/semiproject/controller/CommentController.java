package com.example.semiproject.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.semiproject.dto.CommentDTO;
import com.example.semiproject.dto.MemberDTO;
import com.example.semiproject.service.CommentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {
   
   @Autowired
   private CommentService commentService;
   
//   ------------------------------------------------
//   [댓글 등록] POST /comment/insert
//   @RequestBody - 브라우저가 JSON형식으로 보낸 데이터를 CommentDTO객체에 자동으로 
//               담아줌
//               기존 @ModelAttribute는 HTML 폼 데이터를 받을때 사용
//               (AJAX로 JSON을 보낼 때는 @RequestBody사용)
//   @ResponseBody - 이 메서드의 반환값(Map)을 JSON으로 변환해서 응답으로 보냄
//               화면(HTML)이름을 반환하지 않음
   
   @PostMapping("/insert")
   @ResponseBody
   public Map<String, Object> insert(@RequestBody CommentDTO commentDTO,
                        HttpSession session){
      
//      응답으로 보낼 데이터를 담을 Map 생성
//      Map<String, Object> - 키(문자열) : 값(아무 타입) 쌍으로 구성되는 자료구조
      Map<String, Object> result = new HashMap<>();
      
//      1) 로그인 여부 확인
      MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
      if(loginMember == null) {
//         로그인 안된 상태 - 실패 응답을 반환
//         {"success":false, "message":"로그인이 필요합니다"}
         result.put("success", false);
         result.put("message", "로그인이 필요합니다");
         return result;
      }
      
//      2) 댓글 작성자 번호를 세션에서 꺼내 설정
      commentDTO.setMemberId(loginMember.getMemberId());
      
//      3) DB에 댓글 추가
      commentService.insertComment(commentDTO);
      
//      작성자 아이디를 DTO에 추가
      commentDTO.setMemberLoginId(loginMember.getMemberLoginId());
      
//      4) 작성시간 설정
//      COMMENT_DATE 는 DEFALUT NOW()로 저장되지만,
//      AJAX 응답으로는 DB에서 다시 조회하지 않고 현재 시간을 직접 포맷해서 사용
      String now = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      commentDTO.setCommentDate(now);
      
//      5) 성공 응답 반환
      result.put("success", true);
      result.put("comment", commentDTO);
      return result;
   }
   
//   ---------------------------------------------------------
//   [댓글 삭제] POST /comment/delete/1
//   @ResponseBody  : json으로 응답 반환
   @PostMapping("/delete/{commentId}")
   @ResponseBody
   public Map<String, Object> delete(@PathVariable("commentId") int commentId,
               HttpSession session){
      Map<String, Object> result = new HashMap<>();
      
      // 로그인 여부 확인
      if(session.getAttribute("loginMember") == null) {
         result.put("success", false);
         result.put("message", "로그인이 필요합니다.");
         return result;
      }
      
      // DB에서 댓글 삭제
      commentService.deleteComment(commentId);
      
//      성공 응답 반환
//      {"success" : true}
      result.put("success", true);
      return result;
   }

}








