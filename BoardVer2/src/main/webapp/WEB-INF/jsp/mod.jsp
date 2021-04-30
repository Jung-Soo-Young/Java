<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글 수정</h1>
	<form action="/mod" method = "post">
	<input type = "hidden" name = "no" value = "${param.no}">
		<div>
			제목 : <input type = "text" name = "title" value = "${Data.title}">
		</div>
		<div>
			내용 : <textarea name = "ctnt" rows = "10" cols = "10">"${Data.ctnt}"</textarea>
		</div>
		<div><input type = "submit" value = "글수정"></div>
		<div><input type = "reset" value = "초기화"></div>
	</form>
</body>
</html>