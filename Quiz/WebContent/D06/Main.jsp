<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%if(session.getAttribute("userid")!=null) { %>
<% %>
<a href="/quiz/LoginInfo.do"><input type=button value="입력"></a>
<a href="/quiz/LoginInfo/InputInfo.jsp"><input type=button value="목록"></a>


<%} %>

</body>
</html>