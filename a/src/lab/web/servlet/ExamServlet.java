package lab.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Exam.do")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExamServlet() {
        super();
    }
 
//-----------------------Day02--------------------------
    
	/*
	 (입력하신 숫자는 1, 이숫자는 홀수입니다)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int num = Integer.parseInt(request.getParameter("num"));

		int i = 0;
		i= num/2;
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Form 예제</title>");
		out.println("</head>");
		out.println("<body>");
		out.printf("입력하신 숫자는 %d", num);
		out.print("이 숫자는");
		if(i==0) {
			out.println("짝수");
		}else {
			out.println("홀수");
		}
		out.print("입니다.");
		out.println("</body>");
		out.println("</html>");;
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("num",num);
		
		RequestDispatcher disp = request.getRequestDispatcher("/exam.jsp"); 
		disp.forward(request, response);
		//페이지로 요청을 전송!
		
		
	}

}
