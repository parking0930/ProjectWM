<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/mypage.css">
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<title>WM Project - My page</title>
</head>
<body>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<%
		String id = session.getAttribute("id").toString();
		String email = session.getAttribute("email").toString();
		String nickname = session.getAttribute("nickname").toString();
		boolean fail = request.getParameter("fail")!=null;
	%>
	<div id="center_contents">
		<h2>마이 페이지</h2>
		<form action="editMyinfo" method="post" enctype="multipart/form-data">
			<p class="fonts">아이디</p>
			<div class="text_div"><input class="textboxs" type="text" name="id" value="<%=id %>" disabled></div>
			<p class="fonts">비밀번호</p>
			<div class="text_div"><input class="textboxs" type="password" name="pw"></div>
			<p class="fonts">비밀번호 확인</p>
			<div class="text_div"><input class="textboxs" type="password" name="pwchk"></div>
			<p class="fonts">이메일</p>
			<div class="text_div"><input class="textboxs" type="text" name="email" value="<%=email %>" disabled></div>
			<p class="fonts">닉네임</p>
			<div class="text_div"><input class="textboxs" type="text" name="nickname" value="<%=nickname %>" disabled></div>
			<p class="fonts">프로필  사진</p>
			<div class="text_div"><div id="file_div"><input type="file" name="filename" accept="image/gif,image/jpeg,image/png"></div></div>
			<p style="font-size:13px;">90X90 사이즈의 사진 업로드를 권장합니다.</p>
			<%if(fail){ %>
			<p style="color:red;">비밀번호가 올바르지 않습니다.</p>
			<%} %>
			<div><input type="submit" value="적용" id="reg_submit"></div>
		</form>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>