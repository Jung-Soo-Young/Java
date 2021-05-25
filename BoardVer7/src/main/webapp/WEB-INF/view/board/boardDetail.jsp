<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div><a href = "#" onclick="goBack();">돌아가기</a></div>
<h1>${requestScope.data.title}</h1>
<div>글번호 : ${data.iboard}</div>
<div>작성자 : ${data.writerNm} | 작성일 : ${data.regdt}</div>
<div><c:out value = "${data.ctnt}"/></div>

<div>
	<form id = "cmtFrm" data-iboard="${param.iboard}" onsubmit="return false;">	<!-- data 뒤에 있는 iboard를 js파일에서 dataset 명령을 주게 되면 값을 실행시킨다. -->
		<input type = "text" id = "cmt">				<!-- cmtFrm의 cmt의 value 값 -->
		<input type = "button" value = "댓글달기" onclick = "regCmt();">
	</form>
</div>
<div id = "cmtList"></div>

<script src = "/res/js/boardDetail.js"></script>