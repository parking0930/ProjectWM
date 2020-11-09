package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

@WebServlet("/checknick")
public class checknick extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public checknick() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String changeNick = request.getParameter("changeNick");
		UserDAO user = new UserDAO();
		PrintWriter writer = response.getWriter();
		if(user.checkNickname(changeNick) && !changeNick.equals("")) { // 사용 가능한 닉네임이면
			writer.println("O");
		}else {
			writer.println("X");
		}
	}

}
