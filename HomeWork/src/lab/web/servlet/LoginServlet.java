package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.vo.MemberVO;


@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ArrayList <MemberVO> list = new ArrayList<>(); //MemberServlet에 Static을붙혔기떄문에
												   //MemberServlet의 객체공유할수있음
	
	//MemberServlet mem = new MemberServlet(); //맴버서블릿을 불러와 객체를 만듬 doget dopost 다들어있음
											//static을붙혔으므로 인스턴트를 생성해 호출할 필요가 없음!		
	//mem.list
	public LoginServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/hw/index.jsp");
		//로그아웃 처리
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String auto = request.getParameter("auto");
		
		
		//mem.list//생성한 인스턴스를통해 호출
		for(MemberVO mem : MemberServlet.list) { //클래스이름으로호출
			if(mem.getId().equals(id)&&mem.getPw().equals(pw)) {
				HttpSession session = request.getSession();
				session.setAttribute("userid", id);
				session.setAttribute("name", mem.getName());
				
				//자동로그인을 하겠다 쿠키에저장해서 내보내는 코드
				if(auto!=null) {
					Cookie c1 = new Cookie("id","id");
					Cookie c2 = new Cookie("pw","pw");
					response.addCookie(c1);
					response.addCookie(c2);
				}
				
				response.sendRedirect("/hw/index.jsp");
				return; //redirect후에 dispatcher을섞어서쓰려면 리턴을해줘야ㅏ함
				
			}
		}
		request.setAttribute("message", "아이디 또는 비밀번호가 잘못되었습니다.");
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
		
	}
}
