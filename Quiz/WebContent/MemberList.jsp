<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.MemberVO" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 페이지</title>
</head>
<body>
<h2>회원 목록</h2>
<%--객체 형태로 나왔으므로 객체형태로 형태변경 (ArrayList<MemberVO>)--%>
<%ArrayList<MemberVO> list =(ArrayList<MemberVO>)request.getAttribute("list"); %>
<%for(MemberVO mem : list) { %>
아이디 : <%=mem.getId() %>, 비밀번호 : <%=mem.getPw() %>, 이름 : <%=mem.getName() %> 연락처 : <%=mem.getTel() %>
<br>
<% } %>
<br>
<a href="/quiz/MemberForm.jsp">회원 정보 입력</a>

</body>
</html> 