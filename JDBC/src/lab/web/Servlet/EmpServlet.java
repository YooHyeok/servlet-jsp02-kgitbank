package lab.web.Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.model.*;


@WebServlet("/Emp.do")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    EmpDAO dao;

    public EmpServlet() {//생성자.
        super();
        dao = new EmpDAO(); //생성자실행 드라이버로드실행 후 종료
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String url = " ";
		if("list".equals(action)) {
			ArrayList<EmpVO> list = dao.selectEmployeeList();
			request.setAttribute("list", list);
			url = "/hr/EmpList.jsp";
		}else if ("search".equals(action)) {
			String sempId = request.getParameter("empId");
			int empId = 100; //기본값 100
			if(sempId!=null) {
				int dummy = Integer.parseInt(sempId);
				if (dummy>206 || dummy<100) {//없는 사원번호
				}else {
					empId=dummy;
				}
			}
		
		/*
		if (dummy>207 || dummy<100) {//없는 사원번호
			
		}
		else {
			empId = dummy;
		}
		*/
		EmpVO emp = dao.selectEmployee(empId);
		request.setAttribute("emp", emp);
		url = "/hr/EmpSearch.jsp";

		}
		
		else if("insert".equals(action)) {
//			ArrayList<JobVO> jobList = dao.selectJobs();
//			request.setAttribute("jobList", jobList);
			request.setAttribute("jobList", dao.selectJobs());
			request.setAttribute("deptList", dao.selectDepartments());
			request.setAttribute("manList", dao.selectManagers());
			request.setAttribute("message", "입력");
			request.setAttribute("action", action);
			url = "/hr/EmpInsert.jsp";
		}
		
		else if("view".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			EmpDetailVO emp = dao.selectEmployeeDetails(empId);
			request.setAttribute("emp", emp);
			
			url = "/hr/EmpView.jsp";
		}
		
		else if ("update".contentEquals(action)) {
			EmpVO emp = dao.selectEmployee(Integer.parseInt(request.getParameter("empId")));
			request.setAttribute("emp", emp);
			request.setAttribute("jobList", dao.selectJobs());
			request.setAttribute("deptList", dao.selectDepartments());
			request.setAttribute("manList", dao.selectManagers());
			request.setAttribute("message", "수정");
			request.setAttribute("action", action);

			url = "/hr/EmpInsert.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if("insert".equals(action)) {
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(request.getParameter("empId")));
			emp.setFirstName(request.getParameter("firstName"));
			emp.setLastName(request.getParameter("lastName"));
			emp.setEmail(request.getParameter("email"));
			emp.setPhoneNumber(request.getParameter("phoneNumber"));
			String sdate = request.getParameter("hireDate");
			SimpleDateFormat tool = new SimpleDateFormat("yyyy-MM-dd");
			try {
				emp.setHireDate(new java.sql.Date(tool.parse(sdate).getTime()));
			}catch(ParseException e) {
				
			}
			emp.setJobId(request.getParameter("jobId"));
			emp.setSalary(Double.parseDouble(request.getParameter("salary")));
			emp.setCommissionPct(Double.parseDouble(request.getParameter("commissionPct")));
			emp.setManagerId(Integer.parseInt(request.getParameter("managerId")));
			emp.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
			dao.insertEmployee(emp);
			response.sendRedirect("/JDBC/Emp.do?action=list");
		}else if("update".equals(action)) {
			
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(request.getParameter("empId")));
			emp.setFirstName(request.getParameter("firstName"));
			emp.setLastName(request.getParameter("lastName"));
			emp.setEmail(request.getParameter("email"));
			emp.setPhoneNumber(request.getParameter("phoneNumber"));
			String sdate = request.getParameter("hireDate");
			SimpleDateFormat tool = new SimpleDateFormat("yyyy-MM-dd");
			try {
				emp.setHireDate(new java.sql.Date(tool.parse(sdate).getTime()));
			}catch(ParseException e) {
				
			}
			emp.setJobId(request.getParameter("jobId"));
			emp.setSalary(Double.parseDouble(request.getParameter("salary")));
			emp.setCommissionPct(Double.parseDouble(request.getParameter("commissionPct")));
			emp.setManagerId(Integer.parseInt(request.getParameter("managerId")));
			emp.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
			dao.updateEmployee(emp);
			response.sendRedirect("/JDBC/Emp.do?action=view&empId="+emp.getEmployeeId());
		}
	}

}







