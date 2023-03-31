<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<%if(session.getAttribute("userid")==null){ %>

<%--쿠키를빼는메서드 --%>
<%Cookie[] cookies = request.getCookies();
for(Cookie c : cookies){%>
쿠키 이름 : <%=c.getName() %>, 쿠키 값 : <%=c.getValue() %>
<%} %><br>

<h3>로그인이 되어있지 않습니다. 로그인 해주세요.</h3>
<a href="/hw/Login.jsp">로그인</a>&nbsp;&nbsp;
<a href="/hw/MemberInsert.jsp">회원가입</a>
<%}else { %>
<%Cookie[] cookies = request.getCookies();
for(Cookie c : cookies){%>
쿠키 이름 : <%=c.getName() %>, 쿠키 값 : <%=c.getValue() %>
<%} %><br>
<%=session.getAttribute("name") %>님 환영합니다.<br>
<a href="/hw/Member.do?action=list"><input type=button value=목록조회></a>
<a href="/hw/MemberInsert.jsp"><input type=button value=정보입력></a>
<a href="/hw/Login.do"><input type=button value=로그아웃></a>

<%} %>
</body>
</html>