<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link rel="stylesheet" href="./css/default.css" media="screen">
</head>
<body>
	<table class="layout">
		<tr height="50">
<td><jsp:include page="/incl/header.jsp" />
</td></tr>
<!-- 본문 표시 코드 -->
		<tr height="500" valign="top">
			<td>
<h1>로그인 화면</h1> 
				<c:if test="${!empty message}">
				${message}
				</c:if>
				<h2></h2>
					<h3>아이디와 비밀번호를 입력하세요.</h3>
					<form action='<c:url value="/Login.do"/>' method="post">
						<input type="text" name="userid">
						<input type="password"name="password">
					<input type="submit" vlaue="로그인">
					</form>
</td></tr>
<!-- 본문 표시 코드 -->
<tr height="50">
<td><jsp:include page="/incl/footer.jsp" />
</td></tr>
</table>
</body>
</html>