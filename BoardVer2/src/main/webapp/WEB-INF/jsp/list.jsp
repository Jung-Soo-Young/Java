<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL : jsp파일에 모두 적용시켜야 함! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href = "/write">글쓰기</a>
	
	<div>글 리스트가 보일 부분</div>
	
	<c:forEach var = "i" begin = "0" end = "10"> <!-- forEach구문 숫자 찍을 때 begin, end -->
		<span>${i}</span>
	</c:forEach>
	<table>
		<tr>
			<td>no</td>
			<td>제목</td>
		</tr>
		
		<c:forEach var = "item" items="${list}" varStatus = "status"> <!-- ${list} = BoardList의 키 값 -->
		<tr>
			<td>${status.index}</td> <!-- no값이 index = 0부터 시작, count = 1부터 시작 -->
			<td><a href="/detail?no=${status.index}">${item.title}</a></td>
			<!-- 변수 item의 제목을 클릭하면 detail?no=${status.index}의 값으로 이동 -->
		</tr>
		</c:forEach>
	</table>
</body>
</html>