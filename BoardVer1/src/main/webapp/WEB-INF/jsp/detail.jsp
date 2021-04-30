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
<title>Detail</title>
</head>
<body>
	<h1>Write Detail <%= no %></h1>
	
	<div><%=request.getParameter("no") %></div> <!-- ? -->
	<!-- EL식 -->
	<div>
		<form action = "/del" method = "post">
			<input type = "hidden" name = "no" value = ""> <!-- ? -->
			<input type = "submit" value = "삭제">
		</form>
		<a href = "/mod?no=<%=no %>"><button>수정</button></a>
		
		<!-- a href = "/del?no=<%=no %>"><button>삭제</button></a> = get방식으로 입력받는 것 -->
	</div>
	<div>제목 : <%= vo.getTitle() %></div>
	<div>내용 : <%= vo.getCtnt() %></div>
</body>
</html>