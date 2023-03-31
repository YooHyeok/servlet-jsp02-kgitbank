package lab.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-----------------------Day03--------------------------

@WebServlet("/Data.do")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArrayList<String> list = new ArrayList<>();
	//String = 타입.
	
    public DataServlet() {
    	
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Data 예제</title>");
		out.println("</head>");
		out.println("<body>");
		out.printf("현재까지 입력하신 데이터는 : %s<br>", list.toString());
		out.print("<a href=\"/a/Data.html\">이동</a>");//첫페이지로 이동
		out.println("</body>");
		out.println("</html>");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");

		list.add(data);//만들어놓은 list컬렉션에 data를 담아준다
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Data 예제</title>");
		out.println("</head>");
		out.println("<body>");
		out.print("저장이 완료되었습니다. <a href=\"/a/Data.html\">이동</a>");//첫페이지로 이동
		out.println("</body>");
		out.println("</html>");
		
	}

}
