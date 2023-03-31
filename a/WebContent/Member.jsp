<%-- Day04 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<h2>회원 가입</h2>
<form action="/a/Member.do" method=post>
아이디 : <input type=text name=id><br>
비밀번호 : <input type=password name=pw><br>
이름 : <input type=text name=name><br>
연락처 : <input type=text name=tel><br>
<input type=submit value=가입요청><br>
</form>
</body>
</html>