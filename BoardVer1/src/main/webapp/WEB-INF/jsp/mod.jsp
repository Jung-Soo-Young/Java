<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.koreait.board.BoardVO" %>
<%
	String no = request.getParameter("no"); // 쿼리 스트링 (키 값)을 적어야 함!
	BoardVO vo = (BoardVO) request.getAttribute("data"); // 리턴해주는 값이 Object, vo(주소값) = BoardVO 객체
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글수정</h1>
	<form action="/mod" method="post">
		<input type = "hidden" name = "no" value ="<%=no %>"> <!-- 넘버 값을 받아와야 함! -->
		<div>
			제목 : <input type="text" name = "title" value="<%= vo.getTitle()%>"> <!-- name 속성 값이 중요! = key 값 -->
		</div>
		<div>
			내용 : <textarea name = "ctnt" rows="10" cols="10"><%= vo.getCtnt() %></textarea> <!-- name 속성 값이 중요! = key 값 -->
		</div>
		<div>
			<input type = "submit" value= "글수정">
			<input type = "reset" value= "초기화">
		</div>
	</form>
</body>
</html>