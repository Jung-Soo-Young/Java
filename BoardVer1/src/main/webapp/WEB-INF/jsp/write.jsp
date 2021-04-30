<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form action="/write" method="post">
		<div>
			제목 : <input type="text" name = "title"> <!-- name 속성 값이 중요! = key 값 -->
		</div>
		<div>
			내용 : <textarea name = "ctnt" rows="10" cols="10"></textarea> <!-- name 속성 값이 중요! = key 값 -->
		</div>
		<div>
			<input type = "submit" value= "글쓰기">
			<input type = "reset" value = "초기화">
		</div>
	</form>
</body>
</html>

<!-- 파일쓰기 화면 띄우는 것은 get 방식 --> 
<!-- 내용 처리하는 것은 post 방식 --> 
<!-- Map형식 - Sequence 형색, ArrayList, LinkedList -->