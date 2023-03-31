<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.MemberVO" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 조회 페이지</title>
</head>
<body>
<h2>회원 목록</h2>

<%if(session.getAttribute("userid")!=null) { %>

<a href="/quiz/LoginInfo/InputInfo.jsp"><input type=button value="정보 입력"></a><br>

<%ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list"); %>
<%for(MemberVO mem : list){ %>
아이디 : <%=mem.getId() %><br>
비밀번호 : <%=mem.getPw() %><br>
이름 : <%=mem.getName() %><br>
생년월일 : <%=mem.getBirth() %><br>
<br>
<% } %>
<% } else{ response.sendRedirect("/quiz/LoginInfo/Login.jsp"); }%>


</form>
</body>
</html>