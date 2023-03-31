package lab.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.vo.MemberVO;


@WebServlet("/Member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArrayList<MemberVO> list = new ArrayList<>();
//	MemberVO 는 타입.
	public MemberServlet() {
		super();
//		new LoginInfoServlet().list
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list", list);//request에 저장
		RequestDispatcher disp = request.getRequestDispatcher("/MemberList.jsp");
		disp.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		MemberVO mem = new MemberVO(id,pw,name,tel);
		list.add(mem);
		//리스트를 만들어 저장하기
//		RequestDispatcher disp = request.getRequestDispatcher("/Member.do");//나한테다시보내겠다.
//		disp.forward(request,response); //but post로 요청을 다시찍게됨.
		
		response.sendRedirect("/quiz/Member.do"); //get방식으로 다시날리는 요청~ get은 데이터를 조회하는 용도.
		//요청이 들어와서 응답이나가니 다시요청을보내는행위이므로 경로를 처음부터 다시다찍어야함. /quiz
		
		//요청과 응답과 함께 데이터도 같이 날라가므로 로그인에는 세션과 쿠키를 사용함
	}

}
