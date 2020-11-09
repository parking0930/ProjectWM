package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		String userinfo[];
		UserDAO user = new UserDAO();
		
		boolean result = user.login(id, pw);
		PrintWriter writer = response.getWriter();
		if(result) {
			request.getSession().setAttribute("id", id);
			userinfo = user.getUserData(id);
			String nickname = userinfo[0];
			String email = userinfo[1];
			String point = userinfo[2];
			String profile = userinfo[3];
			String level = userinfo[4];
			String gm = userinfo[5];

			request.getSession().setAttribute("nickname", nickname);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("point", point);
			request.getSession().setAttribute("profile", profile);
			request.getSession().setAttribute("level", level);
			request.getSession().setAttribute("gm", gm);
			response.sendRedirect("./index.jsp");
		}
		else writer.println("<script>alert('아이디 또는 비밀번호가 틀렸습니다.'); history.go(-1);</script>");
	}

}
