<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="./js/store.js" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/store.css">
<title>WM Project - Store</title>
</head>
<body>
	<div id="black_background"></div>
	<div id="popup_contents">
		<div style="text-align:right;">
			<p style="margin-right:20px;margin-top:10px;font-size:20px;font-weight:bold;cursor:pointer;" onclick="exitPop();">X</p>
		</div>
		<div id="textbox_contents">
			<font class="fonts_design">현재 닉네임</font><br>
			<input class="textboxs" type="text" value="<%=session.getAttribute("nickname")%>" disabled><br>
			<font class="fonts_design">변경할 닉네임</font>
			<button type="button" class="btns" style="width:100px;height:25px;" onclick="checkNick()">중복 확인</button>
			<form id="frm" action="./nickSubmit" method="get" accept-charset="utf-8">
			<input class="textboxs" type="text" id="changenick" name="nickname"><br>
			<button type="button" class="btns" style="width:260px;height:25px;" onclick="submit_nick();">적용하기</button>
			</form>
		</div>
	</div>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<%if(session.getAttribute("id")!=null){%>
	<%
		int point = 0;
		int level = Integer.parseInt(session.getAttribute("level").toString());
		point = Integer.parseInt(session.getAttribute("level").toString()) * 100;
	%>
	<div id="center_contents">
		<h3 style="margin:0;">포인트 상점</h3>
		<p style="margin-top:5px;margin-bottom:15px;font-size:13px;">포인트로 원하는 아이템을 구입할 수 있습니다.</p>		
		<div class="store_contents">
			<div class="floating_contents">
				<img style="width:50px;height:50px;border-radius:50%;" src="image/lv.png">
			</div>
			<div class="floating_contents">
				<font class="store_contents_title">레벨 업!</font>
				<font id="store_contents_title">(Lv.<%=level %> > Lv.<%=level+1 %>)</font><br>
				<font class="store_contents_inner">포인트를 사용하여 레벨업을 합니다.</font>
			</div>
			<div class="floating_contents">
				<button class="purchase_btn" type="button" onclick="purchase('levelup');">구매하기<br>(<%=point%>P)</button>
			</div>
		</div>
		<div class="store_contents">
			<div class="floating_contents">
				<img style="width:50px;height:50px;border-radius:50%;" src="image/nick.png">
			</div>
			<div class="floating_contents">
				<font class="store_contents_title">닉네임 변경권</font><br>
				<font class="store_contents_inner">포인트를 사용하여 닉네임을 변경합니다.</font>
			</div>
			<div class="floating_contents">
				<button class="purchase_btn" type="button" onclick="purchase('nickname');">구매하기<br>(100P)</button>
			</div>
		</div>
	</div>
	<%}else{ %>
	<p>로그인이 필요한 서비스입니다.</p>
	<%} %>
	<br>
	<jsp:include page="footer.jsp"/>
</body>
</html>