<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫 JSP</title>
</head>
<body>
<%int total = 0; 
for(int i=1; i<=100; i++)
	total += i;
%>
1부터 100까지의 합은 <%=total %>
</body>
</html>