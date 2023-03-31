package lab.web.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.dao.MemberDAO;
import lab.web.vo.MemberVO;


@WebServlet("/Member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao; //가입 수정 삭제가 들어있는 DAO


	public MemberServlet() {//생성자임
		super();
	}


	public void init(ServletConfig config) throws ServletException {
		dao = new MemberDAO();
	}

	public void destroy() {//
		dao = null; //내가획득한자원을반납하는코드
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); //액션이 뭐냐에 따라 하기위해서
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		MemberVO member = new MemberVO();
		String url="";
		if(action.equals("insert")) {
			request.setAttribute("action", action);
			request.setAttribute("message", "회원 가입");
			url="memberform.jsp";
		}else if(action.equals("update")) {
			try {
				request.setAttribute("member", dao.selectMember(userid));
				request.setAttribute("action", action);
				request.setAttribute("message", "회원 정보 수정");
			}catch(RuntimeException e){
				request.setAttribute("message", e.getMessage());
			}
			url = "/memberform.jsp";
		}else if(action.equals("delete")) {
			request.setAttribute("action", action);
			url = "/board/memberDelete.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		MemberVO mem = new MemberVO();
		mem.setUserid(request.getParameter("userid"));
		mem.setPassword(request.getParameter("password"));
		mem.setName(request.getParameter("name"));
		mem.setEmail(request.getParameter("email"));
		mem.setAddress(request.getParameter("address"));
		String url = "";
		if(action.equals("insert")) {
			dao.insert(mem); //dao에있는 insert에 VO단의 mem에 넣어준 파라미터값들을 넣어줌
			request.setAttribute("message", "회원 가입 성공");
			url = "/Login.jsp";
		}else if(action.equals("update")){
			dao.updateMember(mem);
			response.sendRedirect("/MVC/Board.do?action=member");
			return;
		}else if(action.equals("delete")) {
			HttpSession session = request.getSession();
			String userid=(String)session.getAttribute("userid");
			dao.deleteMember(userid,request.getParameter("password"));
			request.setAttribute("message", "회원 정보 삭제 완료");
			url = "/Login.jsp";
			session.invalidate();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
















