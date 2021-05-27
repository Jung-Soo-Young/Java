<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/res/css/userMypage.css">
<c:choose>
	<c:when test="${empty sessionScope.loginUser.profileImg}">
		<c:set var="img" value = "/res/img/noprofile.jpg"/>
	</c:when>
	<c:otherwise>
		<c:set var="img" value="${sessionScope.loginUser.profileImg}"/>
	</c:otherwise>
</c:choose>
<div>
	<form action = "mypage" method = "post" enctype = "multipart/form-data">
		이미지변경 : <input type = "file" name = "profileImg" accept="image/*">
		<input type = "submit" value = "이미지 업로드">
	</form>
</div>
<div>
	<div><img src = "${img}"></div>
	<div>PK : ${sessionScope.loginUser.iuser}</div>
	<div>ID : ${sessionScope.loginUser.uid}</div>
	<div>Name : ${sessionScope.loginUser.unm}</div>
</div>