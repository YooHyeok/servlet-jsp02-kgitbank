package lab.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.vo.MemberVO;


@WebServlet("/LoginInfo.do")
public class LoginInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ArrayList<MemberVO> list = new ArrayList<>();
       //같이 쓸 수 있음.
    public LoginInfoServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("list", list);
		request.getRequestDispatcher("/LoginInfo/ListSearch.jsp").forward(request, response);
//		RequestDispatcher disp = request.getRequestDispatcher("/ListSearch.jsp");
//		disp.forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String date = request.getParameter("date");
		SimpleDateFormat dateTool = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birth = null; //java.util.Date 형태의 birth라는 변수는 비어있다;
		try {
			birth=dateTool.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MemberVO mem = new MemberVO(id,pw,name,birth);
		list.add(mem);
		request.setAttribute("message", "저장이 완료되었습니다.");
		request.setAttribute("list", "/quiz/LoginInfo.do");
		request.setAttribute("insert", "/quiz/InputInfo.jsp");
		
		request.getRequestDispatcher("/LoginInfo/InputSuccess.jsp").forward(request, response);
		
//		response.sendRedirect("/quiz/LoginInfo.do");
		
	}

}
