<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<script defer src="/res/js/boardList.js"></script>	<!-- defer을 사용하면 위치에 상관없이 마지막에 실행 -->
<link href = "/res/css/boardList.css" rel = "stylesheet">
</head>
<body>
	<div> 로그인 성공 </div>
	<div>${loginUser.unm}님 (${loginUser.uid}) 환영합니다.	<%-- sessionScope.loginUser.unm 가능 --%>
		<a href = "/user/logout">logout</a>
	</div>
	<div>
		<a href = "boardWrite"><button>글쓰기</button></a>
	</div>
	<div>
		리스트
	</div>
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
			</tr>
			<c:forEach items = "${list}" var = "item">	<%-- items = "${requestScope.list}" 가능 --%>
			<tr class = "record" onclick = "moveToDetail(${item.iboard})">
				<td>${item.iboard}</td>
				<td>${item.title}</td>
				<td>${item.unm}</td>
				<td>${item.regdt}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>