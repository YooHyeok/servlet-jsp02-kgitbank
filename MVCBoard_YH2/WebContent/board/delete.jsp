<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>게시글 ${message}</title>
<jsp:include page="/incl/staticHeader.jsp"/>
</head>
<body class="page">
<jsp:include page="/incl/header.jsp"/>
<div id="page-banner" style="background-image: url(./img/photo-1501.jpg);">
  <div class="content  wow fdeInUp">
    <div class="container ">
      <h1>게시판 </h1>
    </div>
  </div>
</div>
<div id="page-body">
  <div class="container">
    <div class="row"> 
      <div class="col-md-offset-3 col-md-6 page-block">
		<h2>게시글 ${message}</h2>
<table class="table table-striped table-bordered">
<h3>회원 비밀번호 입력</h3>
<form action='<c:url value="/Board.do"/>' method="post">
<input type="hidden" name="action" value="${action}">
<input type="hidden" name="bbsno" value="${bbsno}">
<input type="hidden" name="replynumber" value="${replynumber}">
<input type="password" name="password">
<input type="submit" value="삭 제">
</form>
</table>
</div></div></div></div>
 <div class="clearfix"></div>
<jsp:include page="/incl/footer.jsp"/>
</body>
</html>