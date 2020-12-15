package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import userinfo.Member;

@WebServlet("/nickSubmit")
public class nickSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public nickSubmit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String changeNick = request.getParameter("nickname");
		int point = Integer.parseInt(request.getSession().getAttribute("point").toString());
		UserDAO user = new UserDAO();
		PrintWriter writer = response.getWriter();
		if(!changeNick.equals("") && user.checkNickname(changeNick) && point>=100) { // 사용 가능한 닉네임이면
			Member userinfo = new Member();
			userinfo.setId(request.getSession().getAttribute("id").toString());
			userinfo.setNickname(changeNick);
			user.updateNickname(userinfo);
			request.getSession().invalidate();
			writer.println("<script>alert('닉네임 변경이 완료되었습니다.\\n 재로그인 부탁드립니다.'); location.href='./index.jsp';</script>");
		}else {
			writer.println("<script>alert('닉네임 변경에 실패하였습니다.');history.go(-1);</script>");
		}
		user.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
