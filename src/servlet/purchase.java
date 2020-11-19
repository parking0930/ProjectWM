package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

@WebServlet("/purchase")
public class purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public purchase() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		if(request.getParameter("item").equals("levelup")) {
			String id = request.getSession().getAttribute("id").toString();
			String point = request.getSession().getAttribute("point").toString();
			String level = request.getSession().getAttribute("level").toString();
			PrintWriter writer = response.getWriter();
			if(id!=null && point!=null && level!=null && Integer.parseInt(point)>=(Integer.parseInt(level)*100)) {
				UserDAO user = new UserDAO();
				user.levelup(id);
				request.getSession().setAttribute("level", Integer.toString(Integer.parseInt(level)+1));
				request.getSession().setAttribute("point", Integer.toString(Integer.parseInt(point)-(Integer.parseInt(level)*100)));
				writer.println("<script>alert('레벨 업이 완료되었습니다.'); location.href='./store.jsp';</script>");
			}else {
				writer.println("<script>alert('포인트가 부족합니다.'); location.href='./store.jsp';</script>");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
