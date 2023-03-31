<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
<table>
<c:choose>
	<c:when test="${!empty userid}">
	<tr>
	<td colspan=5>${name}님 환영합니다.</td>
	</tr>
	<td><a href='<c:url value="/"/>'>홈으로</td></td>
	<td><a href='<c:url value="/Board.do?action=write"/>'>게시글 쓰기</a></td>
	<td><a href='<c:url value="/Board.do?action=list"/>'>게시글 목록</a></td>
	<td><a href='<c:url value="/Login.do?action=logout"/>'>로그아웃</a></td>
	<td><a href='<c:url value="/Board.do?action=member"/>'>마이페이지</a></td>
	</c:when>
	<c:when test="${empty userid}">
	<td><a href='<c:url value="/Member.do?action=insert"/>'>회원가입</a></td>
	<td><a href='<c:url value="/Login.jsp"/>'>로그인</a></td>
	<tr>
	</c:when>
</c:choose>
</table>
</header>