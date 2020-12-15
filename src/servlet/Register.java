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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		boolean result;
		UserDAO user = new UserDAO();
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pwchk = request.getParameter("pwchk");
		if(!pw.equals(pwchk)) pw = "";
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		Member member = new Member(id, pw, nickname, email);
		
		if(id.equals("") || pw.equals("") || nickname.equals("") || !user.checkID(id) || !user.checkNickname(nickname)) {
			writer.println("<script>location.href='./registerForm.jsp?id="+id+"&email="+email+"&nickname="+nickname+"';</script>");
			return;
		}
		
		result = user.register(member);
		
		if(result) {
			writer.println("<script>alert('회원가입이 완료되었습니다.'); location.href='./index.jsp';</script>");
		}else {
			writer.println("<script>alert('오류 발생!'); history.go(-1);</script>");
		}
		user.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
