<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<h1>로그인</h1>

<%--
<%if(session.getAttribute("userid")==null) { %>

<%=request.getAttribute("message")==null ? "" : request.getAttribute("message") %>

<form action="Login.do" method=post>
아이디 : <input type=text name=id><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=로그인>
<% }else{
response.sendRedirect("/JDBC/index.jsp"); }%>
--%>


<c:choose>
<c:when test="${empty userid}">
<%--
${empty sessionScope.userid}
${empty requestScope.userid}<%--바로 찍어줌 --%>


${message}

<form action="Login.do" method=post>
아이디 : <input type=text name=id><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=로그인>
</form>
</c:when>
<c:otherwise>
<c:redirect url="/index.jsp"/> <%-- <=== (/JDBC)프로젝트주소 필요 없음. 이 프로젝트 안에서만 라이브러리 파일 끌어와서 실행함--%>
<%--
<c:url url="/JDBC/index.jsp"/>
 --%>
</c:otherwise>
</c:choose>
</body>
</html>


</form>
</body>
</html>

