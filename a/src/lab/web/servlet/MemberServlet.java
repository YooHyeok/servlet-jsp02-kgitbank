package lab.web.servlet;

import java.io.IOException;

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

    public MemberServlet() {
        super();
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
//		JSP를통해 입력받은 파라미터들을 Member VO 클래스 에 싹다 넣기
		MemberVO mem = new MemberVO(id,pw,name,tel);
		//넘겨주려는 주소 넣기
		//request에 데이터를 저장하기("이름",변수명)
		request.setAttribute("mem", mem);

		//dispatcher에게 권한을 넘겨준다
		RequestDispatcher disp =  request.getRequestDispatcher("/MemberResult.jsp");
//		주소앞에 /a를 붙히면안됨 붙히게되면 경로를 /a/a/MemberResult로 읽음
//		바깥에 있는 파일은 권한이없음 "/a/MemberResult.jsp" (X)
		
		//넘긴다는 method
		disp.forward(request, response);	
//		disp.include(request, responer);	
		
		//sendRedirect는 요청을 두번 보냄(해당 주소로 다시요청을 보내라)
		//요청 -> 응답-> 다시 요청-> 응답->
		//첫번째와 두번째는 요청과 응답이 서로 다름. 새로운 요청이므로 이어지지않음.
		
//		response.sendRedirect("/a/MemberResult.jsp");
		//요청을 새로 보내라 한마디로 작업을 두번함.
		
		//데이터를 저장해서 계속 쓸거면 Dispacher
		//그게아니면 sendRedirect(사용 예 : 게시판 post에서 get방식으로?)
		
		//주소창에서 ?는 겟방식
		
		//경로주의.
	}

}
