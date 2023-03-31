<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--		 ^		css적용 하겠다 는 내용의 코드		  ^ 			-->
<jsp:include page="/incl/staticHeader.jsp"/>
<title>첫 화면</title>
</head>
<body class="page">

<jsp:include page="/incl/header.jsp"/>

<div id="page-banner" style="background-image: url(img/photo-1501.jpg);">
  <div class="content  wow fdeInUp">
    <div class="container ">
      <font color="white"><h2>게시판에 오신걸 환영합니다.</h2></font>
    </div>
  </div>
</div>

<div id="page-body">
  <div class="container">
    <div class="row"> 
      <div class="col-md-offset-3 col-md-6 page-block">
</div></div></div></div>
 <div class="clearfix"></div>

<jsp:include page="/incl/footer.jsp"/>

</body>
</html>