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
		if(!changeNick.equals("") && user.checkNickname(changeNick) && point>=100) { // ��� ������ �г����̸�
			Member userinfo = new Member();
			userinfo.setId(request.getSession().getAttribute("id").toString());
			userinfo.setNickname(changeNick);
			user.updateNickname(userinfo);
			request.getSession().invalidate();
			writer.println("<script>alert('�г��� ������ �Ϸ�Ǿ����ϴ�.\\n ��α��� ��Ź�帳�ϴ�.'); location.href='./index.jsp';</script>");
		}else {
			writer.println("<script>alert('�г��� ���濡 �����Ͽ����ϴ�.');history.go(-1);</script>");
		}
		user.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
