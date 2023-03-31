package lab.web.Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.model.MemberDAO;


@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       MemberDAO dao;

    @Override
	public void init(ServletConfig config) throws ServletException {
    	dao = new MemberDAO();
    }


	public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/JDBC/index.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("id");
		String userpw = request.getParameter("pw");
		String dbpw = dao.getPassword(userid); //데이터베이스에서 가져온 pw
		if(userpw==null) { //비밀번호를 입력안했다
			request.setAttribute("message", "비밀번호를 입력하세요.");
			
		}else {
			if(dbpw==null) {//끌고 나온 비밀번호가다른건 무조건 아이디가 다른것
				request.setAttribute("message", "아이디가 다릅니다.");
			}else {
				if(userpw.equals(dbpw)) {
					HttpSession session = request.getSession();
					session.setAttribute("userid", userid);
					response.sendRedirect("/JDBC/index.jsp");
					return; //응답메시지가 두번실행되는일이 없도록 if문에리턴
				}else {
					request.setAttribute("message", "비밀번호가 다릅니다.");
				}
			}
		}
		request.getRequestDispatcher("/Login.jsp").forward(request, response);

	}

}
