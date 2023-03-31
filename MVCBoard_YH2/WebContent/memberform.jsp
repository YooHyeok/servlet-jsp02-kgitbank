<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>회원 정보 입력</title>
<jsp:include page="/incl/staticHeader.jsp"/>
</head>
<body class="page">
<jsp:include page="/incl/header.jsp"/>
<div id="page-banner" style="background-image: url(img/photo-1501.jpg);">
  <div class="content  wow fdeInUp">
    <div class="container ">
      <h1>마이페이지</h1>
    </div>
  </div>
</div>
<div id="page-body">
  <div class="container">
    <div class="row"> 
      <div class="col-md-offset-3 col-md-6 page-block">
		<h2>${message}</h2>
		
<table class="table table-striped table-bordered">




<form action="/MVC2/Member.do" method="post">
<fieldset>
<legend>회원 정보 입력 </legend>
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

</table>
</div></div></div></div>
 <div class="clearfix"></div>
<jsp:include page="/incl/footer.jsp"/>
</td></tr>
</body>
</html>