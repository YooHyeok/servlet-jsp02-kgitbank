<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=request.getAttribute("message") %></title>
</head>
<body>
<h1><%=request.getAttribute("message") %></h1>
<a href="<%=request.getAttribute("list")%>">
<input type=button value="목록조회">
</a>
<a href="<%=request.getAttribute("insert")%>">
<input type=button value="정보입력">
</a>
</body>
</html>

