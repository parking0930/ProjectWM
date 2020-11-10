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
<link rel="stylesheet" type="text/css" href="./css/board.css">
<title>WM Project - Board</title>
</head>
<body>
	<%
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String id = request.getParameter("id");
		String nowPage = request.getParameter("page");
		String boardName = "";
		String boardIntro = "";
		String gm;
		UserDAO user = new UserDAO();
		id = id==null ? "free":id;
		nowPage = nowPage==null ? "1":nowPage;
		gm = session.getAttribute("gm")==null ? "":session.getAttribute("gm").toString();
		switch(id){
			case "free":
				boardName="자유게시판";
				boardIntro = "자유롭게 글을 쓸 수 있는 게시판입니다.";
				break;
			case "tactic":
				boardName="공략게시판";
				boardIntro = "게임 공략을 작성할 수 있는 게시판입니다.";
				break;
			case "screenshot":
				boardName="스크린샷";
				boardIntro = "게임 스크린샷을 올릴 수 있는 게시판입니다.";
				break;
			case "notice":
				boardName="공지사항";
				boardIntro = "각종 소식을 받아 볼 수 있는 공지사항 게시판입니다.";
				break;
			case "event":
				boardName="이벤트";
				boardIntro = "이벤트 소식을 받아볼 수 있는 게시판입니다.";
				break;
			default:
				id = "free";
				boardName="자유게시판";
				boardIntro = "자유롭게 글을 쓸 수 있는 게시판입니다.";
				break;
		}
		ArrayList<String> boardArray = user.getBoardData("b_"+id);
		int pageCount = boardArray.size()<=12 ? 1:(boardArray.size()/12)+1;
		int startPageNum = 1;
		int endPageNum = 1;
	%>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<div id="center_contents">
		<%if(id.equals("notice") || id.equals("event")){ %>
		<jsp:include page="left2.jsp"/>
		<%}else{ %>
		<jsp:include page="left.jsp"/>
		<%} %>
		<div id="right_contents">
			<div id="board_div">
				<font style="font-weight:bold;font-size:20px;"><%=boardName %></font>
				<font style="font-size:13px;"> - <%=boardIntro %></font>
				<hr>
				<div id="table_wrap">
					<table style="border-collapse:collapse;">
						<tr>
							<td class="board_table_top" style="width:60px;">번호</td>
							<td class="board_table_top" style="width:340px;">제목</td>
							<td class="board_table_top" style="width:110px;">작성자</td>
							<td class="board_table_top" style="width:90px;">작성일</td>
							<td class="board_table_top" style="width:80px;">조회</td>
						</tr>
						<!-- 12개 최대 -->
					<%if(boardArray.size()>0){
						for(int i=0;i<12;i++){
							if((Integer.parseInt(nowPage)*12)-11+i>boardArray.size())
								break;
							String[] s = boardArray.get((Integer.parseInt(nowPage)*12)-12+i).split("\\|");
							SimpleDateFormat tmpSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
							String WDate = tmpSdf.format(tmpSdf.parse(s[3])).split(" ")[1];
							s[3] = s[3].split(" ")[0];
					%>
						<tr>
							<td class="board_t"><%=s[0] %></td>
							<td class="board_t"><a href="./boardContents.jsp?board=<%=id%>&id=<%=s[0]%>"><%=s[1] %></a>
							<%if(sdf.format(date).equals(s[3])){ %>
								<img style="width:10px;height:10px;" src="image/new.png">
							<%}%>
							</td>
							<td class="board_t"><%=s[2] %></td>
							<%if(sdf.format(date).equals(s[3])){ %>
							<td class="board_t"><%=WDate%></td>
							<%}else{%>
							<td class="board_t"><%=s[3] %></td>
							<%} %>
							<td class="board_t"><%=s[4]%></td>
						</tr>
						<%}
					}%>
					</table>
				</div>
				<div id="board_button">
				<%if(id.equals("notice") || id.equals("event")){
					if(gm.equals("1")){
				%>
					<button class="btns" type="button">글쓰기</button>
				<%	}
				}else{
					if(session.getAttribute("id")!=null){
				%>
					<button class="btns" type="button">글쓰기</button>
					<%}
				} %>
				</div>
				<div id="board_numbering">
					<%if(pageCount>=5){
						for(int i=1;i<=pageCount;i+=5){
							if(Integer.parseInt(nowPage)>=i && Integer.parseInt(nowPage)<=i+5){
								startPageNum = i;
								break;
							}
						}
					%>
					<font><</font>
					<%
						for(int i=0;i<5;i++){
							endPageNum = startPageNum+i;
							if(endPageNum>pageCount)
								break;
							if(endPageNum==Integer.parseInt(nowPage)){%>
								<a style="font-weight:bold;"><%=endPageNum%></a>
							<%}	else{%>
								<a href="./board.jsp?id=<%=id%>&page=<%=endPageNum%>"><%=endPageNum%></a>
							<%}
						}
					}else{
						for(int i=0;i<5;i++){
							endPageNum = startPageNum+i;
							if(endPageNum>pageCount)
								break;
							if(endPageNum==Integer.parseInt(nowPage)){%>
								<a style="font-weight:bold;"><%=endPageNum%></a>
							<%}	else{%>
								<a href="./board.jsp?id=<%=id%>&page=<%=endPageNum%>"><%=endPageNum%></a>
							<%}
						}
					}%>
				</div>
			</div>
		</div>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>