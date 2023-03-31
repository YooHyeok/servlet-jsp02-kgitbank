package lab.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//---------------------Day02 ~ Day 03----------------------

@WebServlet("/Form.do")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FormServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청을 보낼때 응답을 받을때 형식
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		//html에 입력한 모든것은 String으로 받아옴
		//String을 int로 바꿀때 : integer.parseint()
		String date = request.getParameter("date");
		//date = 문자형식으로 출력됨 (1992-10-23)
		//simple date format 날짜를 자바 데이터 형태로 바꾸는 도구 / 매개변수로 날짜 형식. 예 :yyyy-MM-dd
		
		SimpleDateFormat dateTool = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date birth = null;
		
		try {
//			java.util.Date birth = dateTool.parse(date);
			//java.util.date를 밖에서 호출
			birth = dateTool.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String[] hobby = request.getParameterValues("hobby");
		HashMap<String,String> hobbyMap = new HashMap<>();
		hobbyMap.put("1","게임");
		hobbyMap.put("2","피아노");
		hobbyMap.put("3","노래");
		//hobbyMap.put(key, value)
		//key인자에 html에있는 value값을 가져오면 map의 value가 해당됨
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String grade = request.getParameter("grade");
		String tel = request.getParameter("tel");
		String tosay = request.getParameter("tosay");
		String area = request.getParameter("area");
		HashMap<String,String> areaMap = new HashMap<>();
		areaMap.put("1", "서울");
		areaMap.put("2", "대전");
		areaMap.put("3", "대구");
		areaMap.put("4", "부산");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Input 예제</title>");
		out.println("</head>");
		out.println("");
		out.println("</body>");
		out.println("</html>");
		out.printf("이름 : %s<br>",name);
		out.printf("비밀번호 : %s<br>",pw);
		out.printf("생년월일 : %s<br>",birth);
		out.printf("생년월일 : %s<br>",date);
		out.print("취미 : ");
		if(hobby==null) {//아무것도 선택한게 없을 때.
			out.print("선택하신 취미가 없습니다.<br>");
		}else {
			for(String h : hobby) {//취미를 하나씩 꺼내서 h에 key값을 넣음
				out.print(hobbyMap.get(h)+" ");//key값이 하나씩 끌려나옴
			}
			out.println("<br>");
		}
		out.printf("성별 : %s<br>", gender.equals("M") ? "남" : "여");
		//3항 다항식 -> (수식 ? "참" : "거짓") 수식이 참이면 참 거짓이면 거짓이 출력
		out.printf("이메일 : %s<br>", email);
		out.printf("이메일 : %d<br>", Integer.parseInt(grade));
		//스트링을 인트로 바꾸는 메서드. Interger.parseInt()
		out.printf("연락처", tel);
		out.printf("하고 싶은 말<br>--------------<br>"
				+ "<pre>%s</pre>"//내용이 줄이 바뀌는걸 유지해야함 = </pre>
				+ "<br>--------------<br>",tosay); 
		out.printf("지역 : %s",areaMap.get(area));
		//get(area)에 html value값이 들어와서 areaMap의 key값에넣고 value값 출력
	}

}
