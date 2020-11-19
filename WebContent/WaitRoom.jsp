<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/WaitRoom.css">
<title>WM Project - Wait Room</title>
</head>
<body>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("./index.jsp");
%>
	<div id="header">
		<div id="head_profile1">
			<font style="font-weight:bold;">내 정보 |</font>
			<!-- <img style="width:20px;height:20px;border-radius:50%;" src="image/profile/profile.png"> -->
			<font>Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></font>
			<a href="./mypage.jsp" style="color:#6392ba;font-weight:bold;">(마이페이지)</a>
			<button type="button" id="logout_btn" onclick="location.href='./logout';">로그아웃</button>
		</div>
		<div id="head_profile2">
			<font id="nowTime"></font>
		</div>
	</div><br>
	<div id="center_contents">
		<div id="left_contents">
			<h3>방 목록</h3>
			<table id="table_top">
				<tr>
					<td style="width:80px;">번호</td>
					<td style="width:450px;">제목</td>
					<td style="width:170px;">방장</td>
					<td style="width:80px;">인원</td>
				</tr>
			</table>
			<div id="table_wrap_div">
				<table style="border-collapse:collapse;">
					<tr class="tr_style">
						<td style="width:80px;">1</td>
						<td style="width:450px;">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</td>
						<td style="width:170px;">운영자</td>
						<td style="width:80px;">9/9</td>
					</tr>
					<tr class="tr_style">
						<td style="width:80px;">1</td>
						<td style="width:450px;">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</td>
						<td style="width:170px;">운영자</td>
						<td style="width:80px;">9/9</td>
					</tr>
					<tr class="tr_style">
						<td style="width:80px;">1</td>
						<td style="width:450px;">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</td>
						<td style="width:170px;">운영자</td>
						<td style="width:80px;">9/9</td>
					</tr>
					<tr class="tr_style">
						<td style="width:80px;">1</td>
						<td style="width:450px;">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</td>
						<td style="width:170px;">운영자</td>
						<td style="width:80px;">9/9</td>
					</tr>
					<tr class="tr_style">
						<td style="width:80px;">1</td>
						<td style="width:450px;">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</td>
						<td style="width:170px;">운영자</td>
						<td style="width:80px;">9/9</td>
					</tr>
					<tr class="tr_style">
						<td style="width:80px;">1</td>
						<td style="width:450px;">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</td>
						<td style="width:170px;">운영자</td>
						<td style="width:80px;">9/9</td>
					</tr>
					<tr class="tr_style">
						<td style="width:80px;">1</td>
						<td style="width:450px;">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</td>
						<td style="width:170px;">운영자</td>
						<td style="width:80px;">9/9</td>
					</tr>
				</table>
			</div>
			<div id="left_button_div">
				<button class="btns">새로고침</button>
				<button class="btns">방 만들기</button>
			</div>
		</div>
		<div id="right_contetns">
			<div id="connecter_div">
				<div id="connector_top">
					<font style="font-weight:bold;color:white;font-size:14px;"># 접속자 - 0명 </font>
				</div>
				<div id="connector_inner">
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
					<div class="player_connect">· Lv.100 관리자</div>
				</div>
			</div>
			<div id="chat_div">
				<div id="chat_top">
					<font style="font-weight:bold;color:white;font-size:14px;"># 채팅방 </font>
				</div>
				<div id="chat_inner">
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇ</div>
					<!-- ---------------------------------- -->
				</div>
				<input type="text" id="chat_textinput" placeholder="채팅을 입력하세요.">
			</div>
			<div id="exit_btn" onclick="location.href='./index.jsp';">
				<font style="font-weight:bold;font-size:32px;">나가기</font>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var id = <%=session.getAttribute("id").toString()%>;
	var nickname = <%=session.getAttribute("nickname").toString()%>
	var level = <%=session.getAttribute("level").toString()%>
	var profile = <%=session.getAttribute("profile").toString()%>
</script>
<script type="text/javascript" src="./js/socket.js"></script>
<script type="text/javascript" src="./js/WaitRoom.js"></script>
</html>