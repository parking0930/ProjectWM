<%@page import="dao.BoardDAO"%>
<%@page import="boardinfo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/defaultSet.css">
<link rel="stylesheet" type="text/css" href="./css/write.css">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="./js/write.js"></script>
<title>WM Project - Write</title>
</head>
<body>
<%
	if(session.getAttribute("id")==null)
		response.sendRedirect("./index.jsp");
	Board board = new Board();
	String boardP = request.getParameter("board")==null?"free":request.getParameter("board");
	boolean edit = request.getParameter("edit")==null?false:true;
	board.setNameIntro(boardP);
	String title = "";
	String contents = "";
	if(edit){
		board.setDb_name("b_"+boardP);
		board.setId(request.getParameter("id"));
		BoardDAO bDAO = new BoardDAO();
		board = bDAO.getBoardContentsData(board);
		if(!session.getAttribute("nickname").toString().equals(board.getWriter()))
			response.sendRedirect("./index.jsp");
	}
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
			<div id="write_area">
				<font style="font-weight:bold;font-size:20px;"><%=board.getName() %> > 글쓰기</font>
				<hr>
				<form action="./BoardWrite" method="post" id="frm">
				<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
				<input type="hidden" name="board" value="<%=boardP%>">
				<input type="text" name="title" id="title_text" placeholder="제목">
				<button type="button" onclick="img_upload();" id="img_btn"><img style="width:15px;height:15px;"src="./image/imgicon.jpg"> 이미지</button>
				<textarea id="replace_area" name="contents" style="display:none;"></textarea>
				<div contentEditable="true" id="textarea_text"></div>
				<div id="button_area">
					<button type="button" class="btns" onclick="writeSubmit();">작성하기</button>
					<button type="button" class="btns" onclick="history.go(-1);">취소</button>
				</div>
				</form>
			</div>
		</div>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
<%if(edit){%>
<script type="text/javascript">
	document.getElementById('title_text').value = `<%=board.getTitle()%>`;
	document.getElementById('textarea_text').innerHTML = `<%=board.getContents()%>`;
</script>
<%} %>
</html>