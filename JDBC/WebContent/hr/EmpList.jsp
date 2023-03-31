<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "lab.web.model.EmpVO" %>
<%@ page import = "java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<h2>사원 목록</h2>
<%--
<%ArrayList<EmpVO> list = (ArrayList<EmpVO>)request.getAttribute("list");%>
<table>
<tr><td>
<td>사원번호</td><td>이름</td><td>성</td><td>이메일</td><td>전화번호 </td><td>입사일</td>
<td>직무</td><td>급여</td><td>보너스율</td><td>매니저번호</td><td>부서번호</td>
</tr>
<%for(EmpVO emp : list) { %>
<tr>
<td><a href="/JDBC/Emp.do?action=view&empId=<%=emp.getEmployeeId() %>"><%=emp.getEmployeeId() %></a>
<td><%=emp.getEmployeeId() %></td>
<td><%=emp.getFirstName() %></td>
<td><%=emp.getLastName() %></td>
<td><%=emp.getEmail() %></td>
<td><%=emp.getPhoneNumber() %></td>
<td><%=emp.getHireDate() %></td>
<td><%=emp.getJobId() %></td>
<td><%=emp.getSalary() %></td>
<td><%=emp.getCommissionPct() %></td>
<td><%=emp.getManagerId() %></td>
<td><%=emp.getDepartmentId() %></td>
</tr>
<% } %>
 --%>
 <table>
<tr><td>
<td>사원번호</td><td>이름</td><td>성</td><td>이메일</td><td>전화번호 </td><td>입사일</td>
<td>직무</td><td>급여</td><td>보너스율</td><td>매니저번호</td><td>부서번호</td>
</tr>
<c:forEach var="emp" items="${list}">
<tr>
<td><a href="/JDBC/Emp.do?action=view&empId=${emp.employeeId}">${emp.employeeId}</a>
<td>${emp.employeeId}</td>
<td>${emp.firstName}</td>
<td>${emp.lastName}</td>
<td>${emp.email}</td>
<td>${emp.phoneNumber}</td>
<td>${emp.hireDate}</td>
<td>${emp.jobId}</td>
<td>${emp.salary}</td>
<td>${emp.commissionPct}</td>
<td>${emp.managerId}</td>
<td>${emp.departmentId}</td>
</tr>
</c:forEach>

</table>
</body>
</html>