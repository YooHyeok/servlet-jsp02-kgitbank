<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lab.web.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${message} 페이지</title>
</head>
<body>
<h2>사원 정보 ${message}</h2>
<form action="/JDBC/Emp.do" method=post>
<table>
<%-- EL표현식은 있으면나오고 없으면안나오므로 else문의 value값으로 사용하면 ifelse로 나누지않아도되서 if문 싹다지움 --%>
<%--EL표현식의 employeeId는 VO의 get메서드 이름을 찍음 --%>
<tr><td>사원번호</td><td colspan=2><input type=text name=empId value="${emp.employeeId}" ${empty emp ? "" : "readonly" }></td></tr>
								<%--value는 출력해주고 readonly는 삭제나 수정할수없게 막아놓는거임 --%> <%--empty = 널인지아닌지 --%>

<tr><td>이름,성 </td><td><input type=text name=firstName value="${emp.firstName}"></td>
<td><input type=text name=lastName value="${emp.lastName}"></td></tr>
<tr><td>이메일</td><td colspan=2><input type=text name=email value="${emp.email}"></td></tr>
<tr><td>전화번호</td><td colspan=2><input type=text name=tel value="${emp.phoneNumber}"></td></tr>
<tr><td>입사일</td><td colspan=2><input type=date name=hireDate value="${emp.hireDate}"></td></tr>
<tr><td>직무</td><td colspan=2>
<select name=jobId>
<c:forEach var="job" items="${jobList}" >
<option value="${job.jobId}" ${emp.jobId eq job.jobId ? "selected" : ""}>${job.jobTitle}</option>

<%--			  ^ 왼쪽이 넘어가고 	  /	   ^ 오른쪽이 보여줌 		 --%>
<%-- 			  ^ 실제로 선택되는것	  /    ^ 페이지에서 보여지는것 --%>
</c:forEach>
</select></td></tr>
<tr><td>급여</td><td colspan=2><input type=text name=salary value="${emp.salary}"></td></tr>
<tr><td>보너스율</td><td colspan=2><input type=number min=0 max=1 step=0.05 name=commissionPct value="${emp.commissionPct}"></td></tr>
<tr><td>매니저</td><td colspan=2><select name=managerId >
<c:forEach var="man" items="${manList}">
<option value="${man.managerId}"${man.managerId eq emp.managerId ? "selected" : "" }>${man.firstName}</option>
</c:forEach>
</select></td></tr>
<tr><td>부서명</td><td colspan=2><select name=departmentId>

<c:forEach var="dept" items="${deptList}">
<option value="${dept.departmentId}"${dept.departmentId eq emp.departmentId ? "selected" : ""}>${dept.departmentName}</option>
</c:forEach>
</select></td></tr>

<tr><td colspan=3>
<input type=hidden name=action value="${action}">
<input type=submit value="${message}"><input type=reset value=취소>
</table>
</form>
</body>
</html>