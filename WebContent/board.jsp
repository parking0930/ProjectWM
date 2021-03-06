<%@page import="dao.BoardDAO"%>
<%@page import="boardinfo.Board"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UserDAO"%>
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
		Board board = new Board();
		String id = request.getParameter("id")==null?"free":request.getParameter("id");
		String nowPage = request.getParameter("page")==null ? "1":request.getParameter("page");
		String gm = session.getAttribute("gm")==null ? "":session.getAttribute("gm").toString();
		BoardDAO bDAO = new BoardDAO();
		board.setNameIntro(id);
		board.setDb_name("b_"+id);
		ArrayList<Board> boardArray = bDAO.getBoardData(board.getDb_name());
		int pageCount = boardArray.size()<=12 ? 1:(boardArray.size()/12)+(boardArray.size()%12==0?0:1);
		int startPageNum = 1;
		int endPageNum = 0;
	%>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<div id="center_contents">
		<%if(board.getName().equals("공지사항") || board.getName().equals("이벤트")){ %>
		<jsp:include page="left2.jsp"/>
		<%}else{ %>
		<jsp:include page="left.jsp"/>
		<%} %>
		<div id="right_contents">
			<div id="board_div">
				<font style="font-weight:bold;font-size:20px;"><%=board.getName() %></font>
				<font style="font-size:13px;"> - <%=board.getIntro() %></font>
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
							Board tmpBoard = boardArray.get((Integer.parseInt(nowPage)*12)-12+i);
							String commentSize = bDAO.getCommentCount(tmpBoard);
							SimpleDateFormat tmpSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							String dateStr = tmpSdf.format(tmpSdf.parse(tmpBoard.getDate())).split(" ")[0];
							String timeStr = tmpSdf.format(tmpSdf.parse(tmpBoard.getDate())).split(" ")[1];
					%>
						<tr>
							<td class="board_t"><%=tmpBoard.getId() %></td>
							<td class="board_t">
								<a href="./boardContents.jsp?board=<%=id%>&id=<%=tmpBoard.getId()%>"><%=tmpBoard.getTitle() %></a>
								[<%=commentSize%>]
							<%if(sdf.format(date).equals(dateStr)){ %>
								<img style="width:10px;height:10px;" src="image/new.png">
							<%}%>
							</td>
							<td class="board_t"><%=tmpBoard.getWriter() %></td>
							<%if(sdf.format(date).equals(dateStr)){ %>
							<td class="board_t"><%=timeStr%></td>
							<%}else{%>
							<td class="board_t"><%=dateStr %></td>
							<%} %>
							<td class="board_t"><%=tmpBoard.getView()%></td>
						</tr>
						<%}
					}%>
					</table>
				</div>
				<div id="board_button">
				<%if(board.getName().equals("공지사항") || board.getName().equals("이벤트")){
					if(gm.equals("1")){
				%>
					<a href="./write.jsp?board=<%=id%>"><button class="btns" type="button">글쓰기</button></a>
				<%	}
				}else{
					if(session.getAttribute("id")!=null){
				%>
					<a href="./write.jsp?board=<%=id%>"><button class="btns" type="button">글쓰기</button></a>
					<%}
				} %>
				</div>
				<div id="board_numbering">
					<%if(pageCount>=5){
						for(int i=1;i<=pageCount;i+=5){
							if(Integer.parseInt(nowPage)>=i && Integer.parseInt(nowPage)<=i+4){
								startPageNum = i;
								break;
							}
						}
					%>
					<%if(startPageNum!=1){ %>
					<a href="./board.jsp?id=<%=id%>&page=<%=startPageNum-1%>"><</a>
					<%} %>
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
						}%>
					<%if(endPageNum<pageCount){	%>
					<a href="./board.jsp?id=<%=id%>&page=<%=endPageNum+1%>">></a>
					<%} %>
					<%}else{
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