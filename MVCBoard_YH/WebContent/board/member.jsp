<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/default.css" media="screen">
<title>마이페이지</title>
</head>
<body>
<table class="layout">
<tr height="50"><td>
<jsp:include page="/incl/header.jsp"/>
</td></tr>
<tr height="500" valign="top"><td>
<h1>회원 정보</h1>
<h3 align=center>회원정보</h3>
<table>
<tr>
	<th>아이디</th>
	<td>${member.userid}</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td>${member.password}</td>
</tr>
<tr>
	<th>이름</th>
	<td>${member.name}</td>
</tr>
<tr>
	<th>이메일</th>
	<td>${member.email}</td>
</tr>
<tr>
	<th>주소</th>
	<td>${member.address}</td>
</tr>
<tr>
	<th>글 갯수</th>
	<td>${count}</td>
</tr>
<tr>
	<td colspan=2><a href='<c:url value="/Board.do?action=memberList"/>'>목록 확인</a></td>
	</tr>
<tr>
	<td colspan=2>
	<h3 align="center">
	<a href="/MVC/Member.do?action=update"><input type="button" value="회원정보수정"></a>
	&nbsp;&nbsp;
	<a href="/MVC/Member.do?action=delete"><input type="button" value="회원정보삭제"></a>
	</h3>
	</td>
</table>
</td></tr>
<!-- 본문 표시 코드 -->
<tr height="50"><td>
<jsp:include page="/incl/footer.jsp"/>
</td></tr>
</body>
</html>