<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/loginForm.css">
<title>WM Project - Login</title>
</head>
<body>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<div id="center_div">
		<h2>로그인</h2>
		<form action="./login" method="post">
			<div class="login_txt_div"><input type="text" name="id" placeholder="아이디" class="text_design"></div>
			<div class="login_txt_div"><input type="password" name="password" placeholder="비밀번호" class="text_design"></div>
			<div><input type="submit" value="로그인" id="login_submit"></div>
		</form>
		<hr style="width:250px;">
		<div style="margin-bottom:20px;">
			<a href="./register.jsp" class="login_font" style="margin-right:10px;">회원가입</a>
			<a href="#" class="login_font">아이디·비밀번호 찾기</a>
		</div>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>