<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data.title}</title>
<link rel = "stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<style>
	.hidden { display: none; }
	.fa-heart { color : red; }
</style>
<script defer src = "/res/js/boardDetail.js"></script>
</head>
<body>
	<div><a href = "boardList">리스트로 돌아가기</a></div>
	<h1>${data.title} 
		<c:if test ="${data.isFav == 0}">
		<a href = "fav?iboard=${param.iboard}&fav=1"><i class="far fa-heart"></i></a>
		</c:if>
		<c:if test ="${data.isFav == 1}">
		<a href = "fav?iboard=${param.iboard}&fav=0"><i class="fas fa-heart"></i></a>
		</c:if>
	</h1>
	<div>글번호 : ${data.iboard}</div>
	<div>작성자 : ${data.unm} | 작성일 : ${data.regdt}</div>
	<div>${data.ctnt}</div>
	
	<h3>댓글</h3>
	<div>
		<form id = "insFrm" action = "cmt" method = "post">
			<input type = "hidden" name = "icmt" value = "0">
			<input type = "hidden" name = "iboard" value = "${data.iboard}">
			<div>
				<textarea name = "cmt" placeholder = "댓글내용"></textarea>
				<input type = "submit" value = "댓글작성">
			</div>
		</form>
		
		<form id = "updFrm" action = "cmt" method = "post" class = "hidden">
			<input type = "hidden" name = "icmt" value = "0">
			<input type = "hidden" name = "iboard" value = "${data.iboard}">
			<div>
				<textarea name = "cmt" placeholder = "댓글내용"></textarea>
				<input type = "submit" value = "댓글수정">
				<input type = "button" value = "댓글취소" onclick="showInsFrm();">
			</div>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>내용</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>비고</th>
			</tr>
			<c:forEach items = "${cmtList}" var = "item">	<!-- ${requestScope.cmtList} 가능 -->
				<tr>
					<td>${item.cmt}</td>
					<td>${item.unm}</td>
					<td>${item.regdate}</td>
					<td>
						<c:if test = "${item.iuser == loginUser.iuser}">	<!-- 댓글을 작성한 사람과 로그인 사람의 pk값 일치 여부 -->
							<input type = "button" value = "수정" onclick="updCmt(${item.icmt}, '${item.cmt.trim()}');">		<!-- item.cmt는 문자열이기 때문에 ''를 사용 --> <!-- .trim() 양쪽 여백 제거 -->
							<button onclick="delCmt(${data.iboard}, ${item.icmt});">삭제</button>		<!-- data.iboard는 그 다음 화면으로 이동하기 위해 사용 -->
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>