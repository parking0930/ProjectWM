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
<link rel="stylesheet" type="text/css" href="./css/boardContents.css">
<title>Insert title here</title>
</head>
<body>
	<%
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String board = request.getParameter("board");
		String id = request.getParameter("id");
		String boardName;
		String boardIntro;
		String gm;
		UserDAO user = new UserDAO();
		board = board==null ? "free":board;
		id = id==null ? "1":id;
		gm = session.getAttribute("gm")==null ? "":session.getAttribute("gm").toString();
		switch(board){
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
		user.updateView("b_"+board, id);
		String[] bData = user.getBoardContentsData("b_"+board, id).split("\\|");
		String level = user.getLevelProfile(bData[3]).split("\\|")[0];
		String profile = user.getLevelProfile(bData[3]).split("\\|")[1];
		date = sdf.parse(bData[4]);
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
				<font style="margin-bottom:5px;margin-left:5px;font-weight:bold;font-size:14px;color:blue;"># <%=boardName %></font>
				<div style="margin-bottom:5px;">
					<font style="margin-left:5px;font-size:20px;"><%=bData[1]%></font>
				</div>
				<div id="info_area">
					<div style="float:left;margin-left:5px;padding-top:0px;padding-bottom:0px;_border:1px solid red;"><img style="width:40px;height:40px;border-radius:50%" src="./image/profile/<%=profile%>"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:14px;font-weight:bold;">Lv.<%=level %> <%=bData[3] %></font><br>
						<font style="font-size:12px;"><%=sdf.format(date) %></font>
						<font style="font-size:12px;margin-left:1px;">조회  <%=bData[5] %></font>
					</div>
				</div>
				<hr class="hr-1">
				<div style="text-align:right;">
					<a href="http://localhost:8181/ProjectWM/boardContents.jsp?board=<%=board %>&id=<%=id%>" style="font-size:11px;color:silver;margin-right:2px;">http://localhost:8181/ProjectWM/boardContents.jsp?board=<%=board %>&id=<%=id%></a>
				</div>
				<div id="board_inner_contents">
					<!-- 게시글 내용 -->
					<%=bData[2] %>
				</div>
				<div style="margin-top:5px;padding-left:5px;">
					<font style="font-size:14px;font-weight:bold;color:red;">댓글 0</font>
				</div>
			</div>
			<div id="commment_area">
				<%if(session.getAttribute("id")!=null){%>
				<div id="my_comments">
					<div style="float:left;margin-left:5px;padding-top:0px;padding-bottom:0px;"><img style="width:25px;height:25px;border-radius:50%" src="./image/profile/<%=session.getAttribute("profile").toString()%>"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:14px;font-weight:bold;">Lv.<%=session.getAttribute("level").toString() %> <%=session.getAttribute("nickname").toString() %></font><br>
					</div>
					<div id="comment_text_div">
						<textarea id="comment_textfield"></textarea>
					</div>
					<div style="width:690px;display:inline-block;text-align:right;margin-right:10px;">
						<button class="b_btns">작성하기</button>
					</div>
				</div>
				<%} %>
				<div class="comments">
					<div class="comments_img_div"><img class="comments_img" src="./image/profile/profile.png"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:14px;font-weight:bold;">Lv.9999 Example</font><br>
					</div>
					<div class="comments_textarea">
						테스트용 댓글 샘플입니다
					</div>
				</div>
				<div class="comments">
					<div class="comments_img_div"><img class="comments_img" src="./image/profile/profile.png"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:14px;font-weight:bold;">Lv.9999 Example</font><br>
					</div>
					<div class="comments_textarea">
						테스트용 댓글 샘플입니다
					</div>
				</div>
				<div class="comments">
					<div class="comments_img_div"><img class="comments_img" src="./image/profile/profile.png"></div>
					<div style="float:left;margin-left:10px;">
						<font style="font-size:14px;font-weight:bold;">Lv.9999 Example</font><br>
					</div>
					<div class="comments_textarea">
						테스트용 댓글 샘플입니다
					</div>
				</div>
			</div>
		</div><br>
		<div style="display:inline-block;width:1000px;text-align:right;">
			<div style="display:inline-block;margin-right:10px;">
				<%if(session.getAttribute("id")!=null && bData[3].equals(session.getAttribute("nickname").toString())){ %>
				<button class="b_btns">수정</button>
				<button class="b_btns">삭제</button>
				<%} %>
				<button type="button" class="b_btns" onclick="location.href='./board.jsp?id=<%=board%>';">목록</button>
			</div>
		</div>
	</div><br>
	<jsp:include page="footer.jsp"/>
</body>
</html>