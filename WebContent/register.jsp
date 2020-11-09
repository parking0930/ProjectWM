<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/register.css">
<script type="text/javascript" src="./js/register.js"></script>
<title>WM Project - Register</title>
</head>
<body>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<div id="center_contents">
		<h2>이용 약관</h2>
		<div class="box_div">
			<div class="box_title_div">
				<p style="color:white;font-weight:bold;text-align:left;font-size:13px;margin-left:10px;padding-top:7px;">서비스 이용약관 (필수)</p>
			</div>	
			<font class="fonts">WM Project 서비스 이용 약관 입니다.</font>
		</div>
		<div class="checkbox_div">
			<input type="checkbox" name="service" id="service1" style="margin-top:0px;">
           	<label for="service1">동의합니다.</label>
		</div>
		<div class="box_div">
			<div class="box_title_div">
				<p style="color:white;font-weight:bold;text-align:left;font-size:13px;margin-left:10px;padding-top:7px;">개인정보 수집 및 이용 동의 (필수)</p>
			</div>	
			<font class="fonts">WM Project 개인정보 수집 및 이용 동의 약관 입니다.</font>
		</div>
		<div class="checkbox_div">
			<input type="checkbox" name="service" id="service2" style="margin-top:0px;">
           	<label for="service2">동의합니다.</label>
		</div>
           <button id="nextbtn" onclick="nextBtn();">다음 ></button>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>