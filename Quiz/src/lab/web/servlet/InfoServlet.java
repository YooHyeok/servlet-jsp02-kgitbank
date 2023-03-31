package lab.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.vo.InfoVO;
import lab.web.vo.MemberVO;


@WebServlet("/Info.do")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArrayList <InfoVO> list = new ArrayList <>();
    public InfoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list", list);
		request.getRequestDispatcher("/Day06/InfoList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		SimpleDateFormat dateTool = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birth = null; //java.util.Date 형태의 birth라는 변수는 비어있다;
		try {
			birth=dateTool.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String gender = request.getParameter("gender");
		
		String hobby = request.getParameter("hobby");
		HashMap<String,String> hobbyMap = new HashMap<>();
		String area = request.getParameter("area");
		HashMap<String,String> areaMap = new HashMap<>();
		areaMap.put("1", "서울");
		areaMap.put("2", "대전");
		areaMap.put("3", "대구");
		areaMap.put("4", "부산");
		String tosay = request.getParameter("tosay");

		InfoVO info = new InfoVO(id,pw,name,birth,gender,hobby,area,tosay);
		list.add(info);
		response.sendRedirect("/quiz/Info.do");
	}

}
