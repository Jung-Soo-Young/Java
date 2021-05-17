<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div>
		<div> 로그인 화면 </div>
		<div> ${errMsg}</div>
		<div>
			<form action = "userLogin" method = "post">
				<div><input type = "text" name = "uid" placeholder = "id"></div>
				<div><input type = "password" name = "upw" placeholder = "password"></div>
				<div>
					<input type = "submit" value = "login">
				</div>
			</form>
		</div>
		
		<div>
			<a href = "userJoin">join</a>
		</div>
	</div>
</body>
</html>