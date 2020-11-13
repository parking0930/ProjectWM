package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;
import userinfo.Member;

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
		Member userinfo;
		UserDAO user = new UserDAO();
		
		boolean result = user.login(id, pw);
		PrintWriter writer = response.getWriter();
		if(result) {
			userinfo = user.getUserData(id);
			request.getSession().setAttribute("id", userinfo.getId());
			request.getSession().setAttribute("nickname", userinfo.getNickname());
			request.getSession().setAttribute("email", userinfo.getEmail());
			request.getSession().setAttribute("point", userinfo.getPoint());
			request.getSession().setAttribute("profile", userinfo.getProfile());
			request.getSession().setAttribute("level", userinfo.getLevel());
			request.getSession().setAttribute("gm", userinfo.getGm());
			response.sendRedirect("./index.jsp");
		}
		else writer.println("<script>alert('아이디 또는 비밀번호가 틀렸습니다.'); history.go(-1);</script>");
	}

}
