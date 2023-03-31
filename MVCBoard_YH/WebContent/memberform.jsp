<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="./css/default.css" media="screen">

<title>회원 정보 입력</title>
</head>
<body>
<table class="layout">
<tr height="50"><td>
<jsp:include page="/incl/header.jsp"/>
</td></tr>
<tr height="500" valign="top"><td>
<h1>${message}</h1>
<h3 align=center>회원 정보 입력</h3>
<form action="/MVC/Member.do" method="post">
<fieldset>
<legend>회원정보</legend>
<table>
<tr>
	<td>아이디</td>
	<td><input type="text" name="userid" value="${member.userid}"${empty member.userid ? "" : "readonly" }></td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" name="password" value="${member.password}"></td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="name" value="${member.name}"></td>
</tr>
<tr>
	<td>이메일</td>
	<td><input type="text" name="email" value="${member.email}"></td>
</tr>
<tr>
	<td>주소</td>
	<td><input type="text" name="address" value="${member.address}"></td>
</tr>
</table>
<input type="hidden" name="action" value="${action}">
<input type="submit" value="저 장">
<input type="reset" value="취 소">
</fieldset>
</form>
</td></tr>
<tr height="50"><td>
<jsp:include page="/incl/footer.jsp"/>
</td></tr>
</table>
</body>
</html>