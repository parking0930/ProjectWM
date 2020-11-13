<%@page import="userinfo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/ranking.css">
<title>WM Project - Ranking</title>
</head>
<body>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<%
		UserDAO user = new UserDAO();
		ArrayList<Member> userList = user.userRanking();
	%>
	<div id="center_contents">
		<h2>유저 랭킹</h2>
		<div id="table_div">
			<table style="background:#485563;color:white;">
				<tr>
					<th style="width:110px;">순위</th>
					<th style="width:190px;">닉네임</th>
					<th style="width:120px;">레벨</th>
					<th style="width:120px;">포인트</th>
					<th style="width:150px;">가입일</th>
				</tr>
			</table>
			<div style="overflow-y:scroll;height:400px;">
				<table style="border-collapse:collapse;">
				<% for(int i=0;i<userList.size();i++){ 
					Member userinfo = userList.get(i);
				%>
					<tr class="tr_style">
					<%if(i<3){ %>
						<td style="width:110px;padding-top:5px;padding-bottom:5px;"><img style="width:40px;height:40px;" src="./image/<%=i+1%>.png"></td>
					<%}else{ %>
						<td style="width:110px;"><%=i+1 %></td>
					<%} %>
						<td style="width:195px;"><%=userinfo.getNickname() %></td>
						<td style="width:120px;">Lv.<%=userinfo.getLevel() %></td>
						<td style="width:120px;"><%=userinfo.getPoint() %>P</td>
						<td style="width:155px;"><%=userinfo.getDate().split(" ")[0] %></td>
					</tr>
					<%}%>
				</table>
			</div>
		</div>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>