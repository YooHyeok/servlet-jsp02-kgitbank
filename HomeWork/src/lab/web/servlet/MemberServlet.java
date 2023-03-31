package lab.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.vo.MemberVO;


@WebServlet("/Member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ArrayList <MemberVO> list = new ArrayList<>();

	public MemberServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		String url = "";
		
		//제일 중요함
		
		if("list".equals(action)) {//action이list일때
			request.setAttribute("list", list);
			//request.getRequestDispatcher("/MemberList.jsp").forward(request,response);
			//목록을 보겠다.
			url="/MemberList.jsp";
		}else if("view".equals(action)) {
			String id = request.getParameter("id");
			
			HashMap<String,String> hobbyMap = new HashMap<>();
			hobbyMap.put("1","게임");hobbyMap.put("2","피아노");hobbyMap.put("3","노래");
			request.setAttribute("map", hobbyMap);
			
			HashMap<String,String> areaMap = new HashMap<>();
			areaMap.put("1", "서울");areaMap.put("2", "대전");
			areaMap.put("3", "대구");areaMap.put("4", "부산");
			areaMap.put("5", "인천");
			request.setAttribute("map2", areaMap);
			for(MemberVO mem : list) { //리스트에서 mem을뽑아온다.
				if(mem.getId().equals(id)) {
					request.setAttribute("mem", mem);
					break;
				}
			}
			//request.getRequestDispatcher("/Memberview.jsp").forward(request,response);
			url="/MemberView.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
		//dispatcher url을 여러번 요청 응답할때 씀.
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberVO mem = new MemberVO();
		mem.setId(request.getParameter("id"));
		mem.setPw(request.getParameter("pw"));
		mem.setName(request.getParameter("name"));
		SimpleDateFormat tool = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mem.setBirth(tool.parse(request.getParameter("birth")));
		}catch(ParseException e) {

		}
		mem.setGender(request.getParameter("gender").charAt(0)); //charAt = 한글자 빼줌
		/*
		String[] hobby = request.getParameterValues("hobby");//취미를 배열로 받아줌.
		ArrayList a = new ArrayList();
		Object[] b = a.toArray(); //.toArray => 컬렉션을 배열로 받을때.

		List<String> c = Arrays.asList(hobby); //asList(); =>배열을 리스트로 바꿔 줌
		 */
		mem.addHobby(request.getParameterValues("hobby"));
		mem.setArea(request.getParameter("area"));
		mem.setIntroduce(request.getParameter("introduce"));
		list.add(mem);
		//		response.sendRedirect("/hw/Member.do"); //저장 후 겟방식으로 요청을 보내겠다.
		response.sendRedirect("/hw/Member.do?action=list"); 
	}

}
