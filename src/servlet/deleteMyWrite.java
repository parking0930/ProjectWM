package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardinfo.Board;
import dao.BoardDAO;

@WebServlet("/deleteMyWrite")
public class deleteMyWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public deleteMyWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String board = request.getParameter("board");
		String id = request.getParameter("id");
		
		Board boardinfo = new Board();
		boardinfo.setDb_name("b_"+board);
		boardinfo.setId(id);
		
		BoardDAO bDAO = new BoardDAO();
		boardinfo = bDAO.getBoardContentsData(boardinfo);
		
		if(request.getSession().getAttribute("nickname").toString().equals(boardinfo.getWriter())) {
			bDAO.deleteMyWrite(boardinfo);
			bDAO.deleteComments(boardinfo);
			response.sendRedirect("./board.jsp?id="+board);
		}else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
