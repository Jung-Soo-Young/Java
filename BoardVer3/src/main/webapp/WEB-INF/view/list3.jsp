<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	table, th, td {
		border : 1px solid black;
		border-collapse: collapse;
	}
	
	.record {
		cursor : pointer;
	}
	
	.record:hover {
		background-color: #ecf0f1;
	}
</style>
</head>
<body>
	<div>
		<a href = "/write3">글쓰기</a>
	</div>
	<div>
		리스트
	</div>
	<div>
		<table>
			<tr>
				<td>no</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
			
			<c:forEach items="${list}" var="item"> <!-- items = EL식 주소값 -->
				<tr class = "record" onclick="moveToDetail(${item.iboard});">
					<td>${item.iboard}</td>
					<td>${item.title}</td>
					<td>${item.regdt}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		function moveToDetail(iboard) {
			// console.log('iboard : %d', iboard); 클릭할 때마다 주소값 출력 ${param.iboard}
			location.href = '/detail3?iboard=' + iboard;
		}
	</script>
</body>
</html>