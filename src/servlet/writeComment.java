package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commentinfo.Comment;
import user.UserDAO;

@WebServlet("/writeComment")
public class writeComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public writeComment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		UserDAO user = new UserDAO();
		Comment comment = new Comment();
		String id = request.getParameter("id");
		String board = request.getParameter("board");
		comment.setId(id);
		comment.setBoard("b_"+board);
		comment.setComment(request.getParameter("comment").replace("\r\n", "<br>"));
		comment.setWriter(request.getSession().getAttribute("nickname").toString());
		user.insertComment(comment);
		response.sendRedirect("./boardContents.jsp?board="+board+"&id="+id);
	}

}
