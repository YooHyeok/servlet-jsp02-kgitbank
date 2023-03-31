<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
${userid}님 환영합니다

<a href="/JDBC/Login.jsp"><input type=button value=로그인></a>
<a href="/JDBC/Memberform.jsp"><input type=button value=회원가입></a><br>

<a href="/JDBC/Emp.do?action=search"><input type=button value=사원검색></a>
<a href="/JDBC/Emp.do?action=list"><input type=button value=사원목록></a>
<a href="/JDBC/Emp.do?action=insert"><input type=button value=사원정보입력></a>

<a href="/JDBC/Login.do"><input type=button value=로그아웃></a>

</body>
</html>