package lab.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/quiz/LoginInfo/Login.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String userid = "aaaa";
		String userpw = "0000";
		//long start = System.currentTimeMillis(); //시작할때 시간
		//long end = System.currentTimeMillis(); //끝날때 시간

		if(id.equals(userid) && pw.equals(userpw)) { //userid가 입력받아서 받아온 id와 같은지 (pw도)
			//세션은 요청메시지에서 정보를뺄수있음
			//요청메시지에 정보가 담겨져있으므로
			//쿠키는 자동전송
			HttpSession session =request.getSession();
			session.setAttribute("userid", userid); //userid를 세션에 저장.
			
			//session.setMaxInactiveInterval(5); //세션의 유효 시간설정 5초 (5초뒤면 로그인상태에서 튕김)
			//dispatcher은 이어서하라는뜻
			response.sendRedirect("/quiz/LoginInfo/Main.jsp");
		} else {
			request.setAttribute("message", "아이디 또는 비밀번호가 잘못되었습니다.");
			request.getRequestDispatcher("/LoginInfo/Login.jsp").forward(request, response);
		}
	}

}
