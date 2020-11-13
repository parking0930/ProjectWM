package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import user.UserDAO;
import userinfo.Member;

@WebServlet("/editMyinfo")
public class editMyinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public editMyinfo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String profileSave = "/image/profile";
		String real = request.getServletContext().getRealPath(profileSave);
		
		MultipartRequest multi = new MultipartRequest(request, real, 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());

		String pw = multi.getParameter("pw");
		String pwchk = multi.getParameter("pwchk");
		
		if(!pw.equals(pwchk) || pw.equals("") || pw ==null) {
			response.sendRedirect("./mypage.jsp?fail=true");
			return;
		}		
				
		Enumeration files = multi.getFileNames();
		String file = (String)files.nextElement();
		String filename = multi.getFilesystemName(file);
		
		if(filename==null) filename = request.getSession().getAttribute("profile").toString();
		
		UserDAO user = new UserDAO();
		Member userinfo = new Member();
		userinfo.setId(request.getSession().getAttribute("id").toString());
		userinfo.setPw(pw);
		userinfo.setProfile(filename);
		user.updateUserInfo(userinfo);
		
		PrintWriter writer = new PrintWriter(response.getWriter());
		request.getSession().invalidate();
		writer.println("<script>alert('변경이 완료되었습니다.\\n변경된 정보로 재로그인 부탁드립니다.'); location.href='./index.jsp';</script>");
	}

}
