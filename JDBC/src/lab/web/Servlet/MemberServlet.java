package lab.web.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.model.MemberDAO;
import lab.web.model.MemberVO;


@WebServlet("/Member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO dao;

    @Override //서블릿 처음만들어질때 한번만 실행됨
	public void init(ServletConfig config) throws ServletException {
		dao = new MemberDAO();
		
	}


	public MemberServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberVO mem = new MemberVO();
		mem.setUserId(request.getParameter("id"));
		mem.setPassword(request.getParameter("pw"));
		mem.setName(request.getParameter("name"));
		mem.setEmail(request.getParameter("email"));
		mem.setAddress(request.getParameter("address"));
		dao.insertMember(mem);
		request.setAttribute("message", "회원 가입 성공");
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
		
		
	}

}
