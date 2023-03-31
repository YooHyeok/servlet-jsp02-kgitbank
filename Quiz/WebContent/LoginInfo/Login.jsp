<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%--세션에 userid의 유무로 로그인을 판별 --%>
<%if(session.getAttribute("userid")==null) { %> 
<%=(request.getAttribute("message")==null) ? "" : request.getAttribute("message") %>

<%=session.getId()%> <%-- <=세션아이디  지속됨. 서버가 꺼지지않는 이상 사용자를 구분하는 기준이 됨 --%>

<form action="/quiz/Login.do" method=post>
아이디 : <input type=id name=id><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=로그인>
</form>
<% }else { response.sendRedirect("/quiz/LoginInfo/Main.jsp"); } %>
</body>
</html>