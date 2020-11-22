<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/GameRoom.css">
<title>WM Project - Game Room</title>
</head>
<body>
<%if(session.getAttribute("id")==null){%>
	<jsp:forward page="./index.jsp"></jsp:forward>
<%} %>
	<div id="background_div"></div>
	<div id="header">
		<div id="head_profile">
			<font id="my_info_text">내 정보 |</font>
			<font id="my_info_real">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></font>
			<a href="./mypage.jsp" id="my_info_page">(마이페이지)</a>
			<button type="button" id="logout_btn" onclick="location.href='./logout';">로그아웃</button>
		</div>
		<div id="head_time">
			<font id="nowTime"></font>
		</div>
	</div><br>
	<div id="center_contents">
		<div id="left_contents">
			<div id="chat_all_top">채팅방 > Now Waiting</div>
			<div id="chat_all_show">
				<!-- <div class="player_chat_info">
					<div class="player_chat_img_div">
						<img class="player_chat_img" src="image/profile/profile.png">
					</div>
					<div class="player_chat_top">
						Lv.100 운영자
					</div>
				</div>
				<div class="player_chat_all">
					ㅇㅇㅇㅇ
				</div> -->
			</div>
			<div style="margin-top:5px;">
				<input type="text" placeholder="채팅을 입력하세요." id="chat_text_all">
			</div>
		</div>
		<div id="right_contents">
			<div id="connecter_div">
				<div id="connector_top">
					<font id="connect_count"># 접속자 - 0명 </font>
				</div>
				<div id="connector_inner">
					<!-- div class="player_connect">· Lv.100 관리자</div> -->
				</div>
			</div>
			<div id="chat_die_div">
				<div id="chat_die_top">
					<font id="chat_die_font"># 채팅(사망자) </font>
				</div>
				<div id="chat_die_inner">
					<!--
					<div class="player_chat_info">
						<div style="float:left;_border:1px solid red;"><img style="width:20px;height:20px;border-radius:50%;" src="image/profile/<%=session.getAttribute("profile") %>"></div>
						<div style="float:left;margin-left:5px;padding:1px 0px 4px 0px;font-weight:bold;font-size:13px;_border:1px solid red;">Lv.<%=session.getAttribute("level")%> <%=session.getAttribute("nickname")%></div>
					</div>
					<div class="player_chat">ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</div>
					-->
				</div>
				<input type="text" id="chat_die_textinput" placeholder="채팅을 입력하세요.">
			</div>
			<div id="exit_btn" onclick="location.href='./WaitRoom.jsp';">
				<font id="exit_btn_font">나가기</font>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var id = "<%=session.getAttribute("id").toString()%>";
	var nickname = "<%=session.getAttribute("nickname").toString()%>";
	var level = "<%=session.getAttribute("level").toString()%>";
	var profile = "<%=session.getAttribute("profile").toString()%>";
</script>
<script type="text/javascript" src="./js/GameRoomSocket.js"></script>
<script type="text/javascript" src="./js/GameRoom.js"></script>
</html>