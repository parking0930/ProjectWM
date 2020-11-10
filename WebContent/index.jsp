<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/index.css">
<title>WM Project - Main</title>
</head>
<body>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<%
		UserDAO user = new UserDAO();
		ArrayList<String> noticeArray = user.getMainBoardData("b_notice");
		ArrayList<String> freeArray = user.getMainBoardData("b_free");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	%>
	<div id="contents">
		<div id="image_contents">
			<img src="image/image_content.png">
		</div>
		<div id="sub_contents">
			<div class="board_class">
				<div class="board_logo">
					<font class="board_title">공지사항</font>
					<a class="board_link" href="./board.jsp?id=notice">바로가기 ></a>
				</div>
				<div class="board_div">
					<!-- 게시글 내용  최대 8개 -->
				<%for(int i=0;i<noticeArray.size();i++){
					String[] strArr = noticeArray.get(i).split("\\|");
				%>
					<p class="text_dot">· <a class="board_div_text" href="./boardContents.jsp?board=notice&id=<%=strArr[0]%>"><%=strArr[1] %></a>
					<%if(sdf.format(date).equals(strArr[2].split(" ")[0])){ %>
						<img style="width:10px;height:10px;" src="image/new.png">
					<%}%>
					</p>
				<%} %>
				</div>
			</div>
			<div class="board_class">
				<div class="board_logo">
					<font class="board_title">커뮤니티</font>
					<a class="board_link" href="./board.jsp?id=free">바로가기 ></a>
				</div>
				<div class="board_div">
					<!-- 게시글 내용  최대 8개 -->
				<%for(int i=0;i<freeArray.size();i++){
					String[] strArr = freeArray.get(i).split("\\|");
				%>
					<p class="text_dot">· <a class="board_div_text" href="./boardContents.jsp?board=free&id=<%=strArr[0]%>"><%=strArr[1] %></a>
					<%if(sdf.format(date).equals(strArr[2].split(" ")[0])){ %>
						<img style="width:10px;height:10px;" src="image/new.png">
					<%}%>
					</p>
				<%} %>
				</div>
			</div>
			<div id="right_contents">
				<!-- 회원 정보 or 로그인 -->
				<% if(session.getAttribute("id")==null){ %>
				<div id="login_div">
					<p style="font-weight:bold;margin-bottom:5px;">로그인이 필요합니다.</p>
					<button id="login_button_design" type="button" onclick="location.href='./loginForm.jsp'">WM Project 로그인</button>
					<div>
						<a href="./register.jsp" class="login_font" style="margin-right:10px;">회원가입</a>
						<a href="#" class="login_font">아이디·비밀번호 찾기</a>
					</div>
				</div>
				<%}else{ %>
				<div id="client_div">
					<div id="clint_profile_div"><img style="width:90px;height:90px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
					<div id="client_info">
						<font class="client_font" style="font-weight:bold;font-size:15px;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname") %></font><font class="client_font">님</font>
						<p><a href="./mypage.jsp" class="client_font" style="color:#6392ba;font-weight:bold;">마이페이지</a><p>
						<p class="client_font">포인트 : <%=session.getAttribute("point") %>P</p>
						<button type="button" id="logout_btn" onclick="location.href='./logout';">로그아웃</button>
					</div>
				</div>
				<%} %>
				<div id="gamestart_div">
					<button type="button" id="btnGamestart">게임 시작</button>
				</div>
			</div>
		</div>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>