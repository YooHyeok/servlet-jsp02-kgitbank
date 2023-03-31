<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 입력</title>
</head>
<body>
<%if(session.getAttribute("userid")!=null) { %>
<a href="/quiz/LoginInfo/Main.jsp"><input type=button value="메인화면"></a><br>

<a href="/quiz/LoginInfo/login.do"><input type=button value="로그아웃"></a>

<form action="/quiz/LoginInfo.do" method=post>
아이디:<input type=id name=id ><br>
이름:<input type=name name=name ><br>
비밀번호:<input type=password name=pw ><br>
생년월일 :<input type=date name=date ><br>
<input type=submit value=회원가입>
<% } else{ response.sendRedirect("/quiz/LoginInfo/Login.jsp"); }%>
</form>
</body>
</html>