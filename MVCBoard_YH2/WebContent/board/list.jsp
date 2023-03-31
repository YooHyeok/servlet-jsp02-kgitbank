<%@page import="java.io.IOException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ElCeil" uri ="/WEB-INF/tlds/el-function.tld" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
<jsp:include page="/incl/staticHeader.jsp"/></td>
</head>
<body class="page">
<jsp:include page="/incl/header.jsp"/></td>
<div id="page-banner" style="background-image: url(img/photo-1501.jpg);">
  <div class="content  wow fdeInUp">
    <div class="container ">
      <h1>게시판</h1>
    </div>
  </div>
</div>
<div id="page-body">
  <div class="container">
    <div class="row"> 
      <div class="col-md-offset-3 col-md-6 page-block">
		<h2>게시판 목록</h2>
		
<table class="table table-striped table-bordered">
<h3>게시글 목록입니다</h3>

<c:forEach var="board" items="${lists}">
<tr>
<td>${board.name}</td>
<td><a href='<c:url value="/Board.do?action=view&bbsno=${board.bbsno}"/>'>${board.subject}</a></td>
<td>${board.writeDate}</td>
<td>${board.readCount}</td>
</tr>
</c:forEach>
<tr>
<td colspan=4>
<h6 align="center">
	<c:set var="totalPageBlock" value="${ElCeil:ElCeil(totalPageCount/10.0)}"/>
	<c:set var="nowPageBlock" value="${ElCeil:ElCeil(page/10.0)}"/>
	<c:set var="startPage" value="${(nowPageBlock-1)*10+1}"/>
<c:choose>
	<c:when test="${totalPageCount gt nowPageBlock*10}">
	<c:set var="endpage" value="${(nowPageBlock)*10}"/>
	</c:when>
	<c:otherwise>
	<c:set var="endpage" value="${(totalPageCount)}"/>
	</c:otherwise>
</c:choose>
	<c:if test="${nowPageBlock gt 1}">
	<a href='<c:url value="/Board.do?page=${startPage-1}&action=list" />'>◀</a>
	</c:if>
	<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="status">
		[<a href='<c:url value="/Board.do?page=${status.count}&action=list" />'>${status.count}</a>] 
	</c:forEach>
	<c:if test="${nowPageBlock lt totalPageBlock}">
	<a href='<c:url value="/Board.do?page=${endPage+1}&action=list" />'>▶</a>
	</c:if>
</h6>
</td>
</tr>
</table>
</div></div></div></div>
 <div class="clearfix"></div>
<jsp:include page="/incl/footer.jsp"/></td>
</body>
</html>