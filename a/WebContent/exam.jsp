<%-- Day02 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form 예제</title>
</head>
<body>

<form action="/a/Exam.do" method="post">
<%-- 경로앞에 /a 를 붙여야함. 서블릿의 경로는 프로젝트a의 주소가 붙음 --%>
 숫자 입력<input type=text name=num value="정수를 입력하세요">
<input type=submit value=확인>
</form>

<%-- JSP주석 --%>

<%if(request.getAttribute("num")!=null) {%>
<%int num = (int)request.getAttribute("num"); %>
 
입력하신 숫자는<%=num %> 이숫자는 <%int i= num/2; if(i==0) {%>
	짝수
	<%}else {%>
	홀수
<%} %>입니다.
<%} %>

</body>
</html>