<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ page import="lab.web.vo.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
<%--MemberVO mem = request.getAttribute("mem"); --%>
<%MemberVO mem =(MemberVO)request.getAttribute("mem"); %> 
<%--(MemberVO) 붙히는 이유 --%>
가입에 성공하셨습니다. 입력하신 회원 정보는<br>
아이디 : <%=mem.getId() %><br>
비밀번호 : <%=mem.getPw() %><br>
이름 : <%=mem.getName() %><br>
연락처 : <%=mem.getTel() %><br>
</body>
</html>