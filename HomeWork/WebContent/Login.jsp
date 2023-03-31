<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%if(session.getAttribute("userid")==null) { %>
<h1>로그인</h1>
<form action="/hw/Login.do" method="post">
<%--Day08 자동로그인 --%>
<input type=checkbox name=auto value=1>자동로그인<br>

아이디 : <input type=text name=id><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=로그인>

</form>
<%=request.getAttribute("message")==null ? "" : request.getAttribute("message")%>
<%}else {response.sendRedirect("/hw/index.jsp"); }%>
</body>
</html>