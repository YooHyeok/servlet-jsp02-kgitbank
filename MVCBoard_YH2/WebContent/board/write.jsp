<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<title>${message}</title>


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
		<h2>게시글 ${message}</h2>
		
<table class="table table-striped table-bordered">


<form action='<c:url value="/Board.do"/>' method="post">
<table>
<tr>
<td>제목</td>
<td><input type="text" name="subject" size="20" value="${board.subject}"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="password" value="${board.password}"></td>
</tr>
<tr>
<td>내용</td>
<td><textarea cols="30" rows="5" name="content" >${board.content}"></textarea></td>
</tr>
<tr>
<td cols="2" ><input type="hidden" name="action" value="${action}">
<input type="hidden" name="bbsno" value="${board.bbsno}">
<input type="hidden" name="masterid" value="${board.masterId}">
<input type="hidden" name="replynumber" value="${board.replyNumber}">
<input type="hidden" name="replystep" value="${board.replyStep}">
<input type="hidden" name="userid" value="${board.userId}">
<input type="submit" value="저 장"> <td>
<input type="reset" value="취 소">
</tr>
</table>
</div></div></div></div>
 <div class="clearfix"></div>
<jsp:include page="/incl/footer.jsp"/>
</td></tr>
</table>
</body>
</html>