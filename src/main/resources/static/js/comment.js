// ======================================
// comment.js - 댓글 Ajax 처리(board/detail.html에서 사용)
// ======================================

// 댓글 수 표시 업데이터
// delta +1 이면 등록, -1 이면 삭제
function updateCommentCount(delta){
   const countEl = document.getElementById("commentCount");
   // parseInt() - 화면의 텍스트 '3'을 숫자 3으로 변환
   countEl.textContent = parseInt(countEl.textContent) + delta;
}

// 새 댓글 DOM 요소를 댓글 목록에 추가
// comment - 서버가 json으로 응답한 댓글 데이터(commentDTO 필드와 동일)
function appendComment(comment){
   
   // 현재 로그인 사용자 ID를 hidden input에서 읽어옴
   // <input type="hidden" id="loginMemberId" th:value="${loginMemberId}">
   const loginMemberId = parseInt(document.getElementById('loginMemberId').value);
   
   // 새 div 태그를 만듦(아직 화면에 없음)
   const div = document.createElement('div');
   div.className = 'comment-item';
   div.id = 'comment-' + comment.commentId; // id로 나중에 찾아 삭제하기 위해
   
   // 본인 댓글이면 삭제 버튼 html 추가 
   const deleteBtn = (comment.memberId == loginMemberId)?
      `<button class="btn btn-sm btn-danger" onclick="deleteComment(${comment.commentId})">삭제</button>`
      : '';
   
   // innerHTMl - div안에 html 내용을 설정
   div.innerHTML = 
      '<span class="comment-author">' + comment.memberLoginId + '</span>' +
      '<span class="comment-cotent">' + comment.commentContent + '</span>' +
      '<span class="comment-date">' + comment.commentDate + '</span>' +
      deleteBtn;
   
   // 만든 div를 실제 댓글 목록(commentList)에 추가 -> 화면에 나타남
   document.getElementById("commentList").appendChild(div);
}

// 댓글 등록(AJAX)
// async - 이 함수 안에서 await 를 사용하겠다는 선언
async function submitComment(){
   
   // hidden input에서 게시글 번호와 댓글 내용을 읽어옴
   const boardId = document.getElementById('boardId').value;
   const contentInput = document.getElementById('commentContent');
   const content = contentInput.value.trim(); // trim() : 앞뒤 공백 제거
   
   if(!content){
      alert("댓글 내용을 입력해주세요!");
      return;
   }
   
   /*
      fetch() - 브라우저 내장 비동기 http 요청 함수
      await - 서버 응답이 올때까지 이 줄에서 기다림(다음 줄은 응답 후 진행)
      
      [요청 옵션]
      method : 'POST' - 전송 방식
      headers.Content-Type  - 내가 보내는 데이터의 형식을 서버에 알림
         'application/json'  -> 본문이 json 형식임을 명시(필수)
      body : JSON.stringify(...) -> js객체를 JSON 문자열로 변환하여 전송 
      ex) {boardId : 3, commentContent : '안녕'}
      -> {'boardId' : '3', 'commentContent' : '안녕'}
   */
   
   try{
      const response = await fetch("/comment/insert", {
         method : 'POST',
         headers:{'Content-Type':'application/json'},
         body: JSON.stringify({
            boardId: parseInt(boardId), // 숫자형으로 변환
            commentContent: content
         })
      });
      
      // response.json() - 서버가 보낸 JSON 문자열을 javaScript 객체로 변환
      // await - 변환이 완료될때까지 기다림
      const result = await response.json();
      console.log(result);
      
      if(result.success){
         appendComment(result.comment); // 댓글 목록에 새 댓글 추가
         contentInput.value = ''; // 입력창 초기화
         updateCommentCount(1);  // 댓글 수 + 1
      }else{
         alert(result.message || '댓글 등록에 실패했습니다.');
      }
      
   } catch(error){
      // 네트워크 오류 등 예외 상황 처리
      alert("오류가 발생했습니다. 다시 시도해주세요.");
      console.error(error); 
   }
}

// 댓글 삭제(AJAX)
// commentId - 삭제할 댓슬 번호(detail.html에서 onclick으로 전달)
async function deleteComment(commentId){
   if(!confirm("댓글을 삭제하시겠습니까?")){
      return;
   }
   try{
      const response = await fetch("/comment/delete/"+commentId,{
         method: 'POST'
      });
      
      const result = await response.json();
      
      if(result.success){
         // id = comment- {commentId}로 설정된 DOM요소 찾아서 제거
         document.getElementById('comment-'+commentId).remove();
         updateCommentCount(-1); // 댓글 수 -1
      } else{
          alert("댓글 삭제에 실패했습니다.");
      }
      
      
   }catch(error){
      alert("오류가 발생했습니다. 다시 시도해주세요.");
      console.error(error);
   }
   
   
}


















