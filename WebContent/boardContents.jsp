<%@page import="dao.BoardDAO"%>
<%@page import="commentinfo.Comment"%>
<%@page import="userinfo.Member"%>
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
<link rel="stylesheet" type="text/css" href="./css/boardContents.css">
<title>WM Project</title>
</head>
<body>
	<%
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Member writerInfo = new Member();
		Board board = new Board();
		ArrayList<Comment> commentList;
		UserDAO user = new UserDAO();
		BoardDAO bDAO = new BoardDAO();
		board.setId(request.getParameter("id")==null ? "1":request.getParameter("id"));
		String boardP = request.getParameter("board")==null ? "free":request.getParameter("board");
		String gm = session.getAttribute("gm")==null ? "":session.getAttribute("gm").toString();
		board.setNameIntro(boardP);
		board.setDb_name("b_"+boardP);
		bDAO.updateView(board.getDb_name(), board.getId());
		board = bDAO.getBoardContentsData(board);
		commentList = bDAO.getComment(board);
		writerInfo = user.getLevelProfile(board.getWriter());
	%>
	<jsp:include page="header.jsp"/><br>
	<jsp:include page="menubar.jsp"/><br>
	<div id="center_contents">
	<%if(board.equals("notice") || board.equals("event")){ %>
		<jsp:include page="left2.jsp"/>
	<%}else{ %>
		<jsp:include page="left.jsp"/>
	<%} %>
		<div id="right_contents">
			<div id="board_div">
				<font style="margin-bottom:5px;margin-left:5px;font-weight:bold;font-size:14px;color:blue;"># <%=board.getName() %></font>
				<div style="margin-bottom:5px;">
					<font style="margin-left:5px;font-size:20px;"><%=board.getTitle()%></font>
				</div>
				<div id="info_area">
					<div style="float:left;margin-left:5px;padding-top:0px;padding-bottom:0px;_border:1px solid red;"><img style="width:40px;height:40px;border-radius:50%" src="./image/profile/<%=writerInfo.getProfile()%>"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:14px;font-weight:bold;">Lv.<%=writerInfo.getLevel() %> <%=writerInfo.getNickname() %></font><br>
						<font style="font-size:12px;"><%=sdf.format(sdf.parse(board.getDate())) %></font>
						<font style="font-size:12px;margin-left:1px;">조회  <%=board.getView() %></font>
					</div>
				</div>
				<hr class="hr-1">
				<div style="text-align:right;">
					<a href="http://localhost:8181/ProjectWM/boardContents.jsp?board=<%=boardP %>&id=<%=board.getId()%>" style="font-size:11px;color:silver;margin-right:2px;">
						http://localhost:8181/ProjectWM/boardContents.jsp?board=<%=boardP%>&id=<%=board.getId()%>
					</a>
				</div>
				<div id="board_inner_contents">
					<!-- 게시글 내용 -->
					<%=board.getContents() %>
				</div>
				<div style="margin-top:5px;padding-left:5px;">
					<font style="font-size:14px;font-weight:bold;color:red;">댓글 <%=commentList.size() %></font>
				</div>
			</div>
			<div id="commment_area">
				<%if(session.getAttribute("id")!=null){%>
				<div id="my_comments">
					<div style="float:left;margin-left:5px;padding-top:0px;padding-bottom:0px;"><img style="width:25px;height:25px;border-radius:50%" src="./image/profile/<%=session.getAttribute("profile").toString()%>"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:14px;font-weight:bold;">Lv.<%=session.getAttribute("level").toString() %> <%=session.getAttribute("nickname").toString() %></font><br>
					</div>
					<form action="./writeComment" method="post">
					<div id="comment_text_div">
						<textarea id="comment_textfield" name="comment"></textarea>
					</div>
					<div style="width:690px;display:inline-block;text-align:right;margin-right:10px;">
						<input type="hidden" name="board" value="<%=boardP%>">
						<input type="hidden" name="id" value="<%=board.getId()%>">
						<input type="submit" class="b_btns" value="작성하기">
					</div>
					</form>
				</div>
				<%} %>
				<%for(int i=0;i<commentList.size();i++){
					Comment comment = commentList.get(i);
					Member tmpMember =  user.getLevelProfile(comment.getWriter());
				%>
				<div class="comments">
					<div class="comments_img_div"><img class="comments_img" src="./image/profile/<%=tmpMember.getProfile()%>"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:13px;font-weight:bold;">Lv.<%=tmpMember.getLevel() %> <%=comment.getWriter() %></font><br>
						<font style="font-size:11px;"><%=comment.getDate() %></font>
					</div>
					<div class="comments_textarea">
						<font style="font-size:13px;">
							<%=comment.getComment() %>
						</font>
					</div>
				</div>
				<%} %>
			</div>
		</div><br>
		<div style="display:inline-block;width:1000px;text-align:right;">
			<div style="display:inline-block;margin-right:10px;">
				<%if(session.getAttribute("id")!=null && board.getWriter().equals(session.getAttribute("nickname").toString())){ %>
				<button class="b_btns" onclick="gotoEdit();">수정</button>
				<button class="b_btns" onclick="deleteMyWrite();">삭제</button>
				<%} %>
				<button type="button" class="b_btns" onclick="gotoBoard();">목록</button>
			</div>
		</div>
	</div><br>
	<%
		bDAO.close();
		user.close();
	%>
	<jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript">
	document.title = "<%=board.getTitle()%> : WM Projcet - <%=board.getName()%>";
	function deleteMyWrite(){
		if(confirm("게시글을 삭제할까요?")){
			location.href='./deleteMyWrite?board=<%=boardP%>&id=<%=board.getId()%>';
		}else{
			return;
		}
	}
	function gotoBoard(){
		location.href='./board.jsp?id=<%=boardP%>';
	}
	function gotoEdit(){
		location.href='./write.jsp?board=<%=boardP%>&id=<%=board.getId()%>&edit=true';
	}
</script>
</html>