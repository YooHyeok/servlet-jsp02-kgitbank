<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.model.EmpVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 검색 페이지</title>
</head>
<body>
<h3>조회하려는 사원의 사원번호를 입력해주세요.</h3>
<form action="/JDBC/Emp.do">
<input type=hidden name=action value=search> 
사원번호 : <input type=text name=empId><input type=submit value=검색>
</form>
<%--
<%EmpVO emp = (EmpVO)request.getAttribute("emp"); %>
사원번호 : <%=emp.getEmployeeId() %>,이름:<%=emp.getFirstName() %>,
성 : <%=emp.getLastName() %>, 이메일:<%=emp.getEmail() %>,
연락처 : <%=emp.getPhoneNumber() %>, 입사일 : <%=emp.getHireDate() %>,
직무 : <%=emp.getJobId() %>, 급여 : <%=emp.getSalary() %>,
보너스율 : <%=emp.getCommissionPct() %>, 매니저 : <%=emp.getManagerId() %>,
부서 : <%=emp.getDepartmentId() %>
--%>
사원번호 : ${emp.employeeId}<br>
이름 : ${emp.firstName}<br>
성 : ${emp.lastName}<br>
이메일: ${emp.email}<br>
연락처 : ${emp.phoneNumber}<br>
입사일 : ${emp.hireDate}<br>
직무 : ${emp.jobId}<br>
급여 : ${emp.salary}<br>
보너스율 : ${emp.commissionPct}<br>
매니저 : ${emp.managerId}<br>
부서 : ${emp.departmentId}<br>


</body>
</html>

<!--여기부터 시작하면 X EmpVO emp = (EmpVO)request.getAttribute("emp"); 얘가 없음 -->
