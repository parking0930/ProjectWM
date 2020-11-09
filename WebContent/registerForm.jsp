<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/registerForm.css">
<title>WM Project - Register</title>
</head>
<body>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<%
		String id, nickname, email;
		id = request.getParameter("id")!=null ? request.getParameter("id"):"";
		nickname = request.getParameter("nickname")!=null ? request.getParameter("nickname"):"";
		email = request.getParameter("email")!=null ? request.getParameter("email"):"";
	%>
	<div id="center_contents">
		<h2>회원 가입</h2>
		<form action="Register">
			<p class="fonts">아이디</p>
			<div class="text_div"><input class="textboxs" type="text" name="id" value="<%=id %>"></div>
			<p class="fonts">비밀번호</p>
			<div class="text_div"><input class="textboxs" type="password" name="pw"></div>
			<p class="fonts">비밀번호 확인</p>
			<div class="text_div"><input class="textboxs" type="password" name="pwchk"></div>
			<p class="fonts">이메일</p>
			<div class="text_div"><input class="textboxs" type="text" name="email" value="<%=email %>"></div>
			<p class="fonts">닉네임</p>
			<div class="text_div"><input class="textboxs" type="text" name="nickname" value="<%=nickname %>"></div>
			<%if(!id.equals("")||!nickname.equals("")||!email.equals("")){ %>
			<p style="color:red;">입력한 정보가 올바르지 않습니다.</p>
			<%} %>
			<div><input type="submit" value="가입하기" id="reg_submit"></div>
		</form>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>