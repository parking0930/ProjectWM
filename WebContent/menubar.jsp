<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="./js/menubar.js"></script>
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/menubar.css">
<title>메뉴</title>
</head>
<body>
	<div id="menubar">
		<ul>
			<a href="./index.jsp"><li>메 인</li></a>
			<a href="./board.jsp?id=notice"><li>공지사항</li></a>
			<a href="./board.jsp?id=free"><li>커뮤니티</li></a>
			<a href="./ranking.jsp"><li>랭 킹</li></a>
			<a href="./store.jsp"><li>상 점</li></a>
			<a href="#"><li>게임 방법</li></a>
			<a href="#"><li>고객 센터</li></a>
		</ul>
	</div>
	<div id="menuslider">
		<div>
			<div style="width:122px;margin-left:10px;float:left;">
				<a href="./index.jsp"><p class="menuslider_font">메인</p></a>
			</div>
			<div style="width:149px;float:left;">
				<a href="./board.jsp?id=notice"><p class="menuslider_font">공지사항</p></a>
				<a href="./board.jsp?id=event"><p class="menuslider_font">이벤트</p></a>
			</div>
			<div style="width:149px;float:left;">
				<a href="./board.jsp?id=free"><p class="menuslider_font">자유게시판</p></a>
				<a href="./board.jsp?id=tactic"><p class="menuslider_font">공략게시판</p></a>
				<a href="./board.jsp?id=screenshot"><p class="menuslider_font">스크린샷</p></a>
			</div>
			<div style="width:124px;float:left;">
				<a href="./ranking.jsp"><p class="menuslider_font">랭킹</p></a>
			</div>
			<div style="width:124px;border;float:left;">
				<a href="./store.jsp"><p class="menuslider_font">포인트샵</p></a>
			</div>
			<div style="width:154px;float:left;">
				<a href="#"><p class="menuslider_font">게임 소개</p></a>
				<a href="#"><p class="menuslider_font">게임 규칙</p></a>
			</div>
			<div style="width:156px;float:left;">
				<a href="#"><p class="menuslider_font">Q & A</p></a>
				<a href="#"><p class="menuslider_font">1대1 문의</p></a>
			</div>
		</div>
	</div>
</body>
</html>