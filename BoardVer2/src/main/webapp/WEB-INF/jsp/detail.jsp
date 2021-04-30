<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = "홍길동";
	pageContext.setAttribute("name", "A");
	request.setAttribute("name", "B");
	session.setAttribute("name", "C");
	application.setAttribute("name", "D");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
</head>
<body>
	<div> 쿼리 스트링 : ${param.no}, ${param.age} </div> <!-- 쿼리 스트링 param은 setAttribute의 키 값으로 사용 불가능 -->	
	<div> 제목 : ${vo.title} </div> <!-- ${vo.title} = EL식, vo = BoardDetailServlet의 setAttribute의 키 값 , .필드명 = 멤버 필드명 -->
	<div> 내용 : ${vo.ctnt} </div>
	<div>
		<form action = "/del" method = "post">
			<input type = "hidden" name = "no" value = "${param.no}">
			<input type = "submit" value = "삭제">
		</form>
		<a href="/mod?no=${param.no}"><button>수정</button></a>
		
	</div>
</body>
</html>
