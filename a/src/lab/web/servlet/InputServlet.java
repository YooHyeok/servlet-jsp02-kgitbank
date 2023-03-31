package lab.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-----------------------Day02--------------------------

@WebServlet("/Input.do")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InputServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
		
		
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Input 예제</title>");
	out.println("</head>");
	out.println("");
	out.printf("이름 %s. 비밀번호 %s",name,password);
	out.println("</body>");
	out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Input 예제</title>");
		out.println("</head>");
		out.println("");
		out.printf("이름 %s. 비밀번호 %s",name,password);
		out.println("</body>");
		out.println("</html>");
	}

}
