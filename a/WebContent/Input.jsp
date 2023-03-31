<%-- Day04 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/a/Input.jsp">
아이디 : <input type=text name=id><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=입력>
<br><br><br>
<%String id = request.getParameter("id"); 
String pw = request.getParameter("pw"); %>
아이디 : <%=id %>, 비밀번호 : <%=pw %>

</form>
</body>
</html>