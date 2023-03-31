<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.InfoVO" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 조회</title>
</head>
<body>
<h2>목록</h2>

<%if(session.getAttribute("userid")!=null) { %>
<%ArrayList<InfoVO> list = (ArrayList<InfoVO>)request.getAttribute("list"); %>
<%for(InfoVO info : list){ %>
이름 : <%=info.getId() %>
생년월일 : <%=info.getBirth() %>
성별 : <%=info.getGender() %>

<%} %>
<%} else{ response.sendRedirect("/quiz/D06/Login.jsp"); }%>



</body>
</html>