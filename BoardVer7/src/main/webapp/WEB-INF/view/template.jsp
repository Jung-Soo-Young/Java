<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/res/css/common.css">
<script defer src = "/res/js/common.js"></script> <!-- common.js = detail.jsp에서 사용하기 위함 -->
<title>${requestScope.title}</title>
</head>
<body>
	<header>
		<nav>
			<ul>
				<c:if test="${empty sessionScope.loginUser}">		<!--  empty = 비어있는지 여부 확인 (로그인 정보) -->
					<li><a href="/user/login">로그인</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.loginUser}">
					<li><a href="/user/logout">로그아웃</a></li>
					<li><a href="/board/write">글쓰기</a></li>
					<li><a href="/user/mypage">마이페이지</a></li>
				</c:if>
				<li><a href="/board/list">리스트</a></li>
			</ul>
		</nav>
	</header>
	<section>
		<jsp:include page="/WEB-INF/view/${requestScope.jsp}.jsp"></jsp:include>
		<!-- jsp:include 함수 사용 requedstScope.jsp = MyUtils의 OpneJSP에서 jsp파일을 불러옴 -->
	</section>
</body>
</html>