package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardinfo.Board;
import dao.BoardDAO;

@WebServlet("/BoardWrite")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardWrite() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BoardDAO bDAO = new BoardDAO();
		Board board = new Board();
		String id = request.getParameter("id").equals("null")?"":request.getParameter("id");
		String boardName = request.getParameter("board");
		board.setDb_name(boardName==null?"b_free":"b_"+boardName);
		board.setTitle(request.getParameter("title").equals("")?"제목없음":request.getParameter("title"));
		board.setContents(request.getParameter("contents").equals("")?"내용 없음":request.getParameter("contents"));
		board.setWriter(request.getSession().getAttribute("nickname").toString());
		if(id.equals("")) {
			bDAO.insertBoard(board);
			board = bDAO.getMyWrite(board);
			response.sendRedirect("./boardContents.jsp?board="+boardName+"&id="+board.getId());
		}else {
			bDAO.updateMyWrite(board, id);
			response.sendRedirect("./boardContents.jsp?board="+boardName+"&id="+id);
		}
	}

}
