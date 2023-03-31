<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="top-menu" class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html"><img
				src="./img/logo-top.png" class="img-responsive"><span>Grit</span></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<!--nav icon-->
			<a id="nav-icon"> <span></span> <span></span> <span></span>
			</a>
			<!--nav icon end-->
			<c:choose>
				<c:when test="${!empty userid}">
					<ul id="nav-top" class="nav navbar-nav navbar-right">
						<li><a href='<c:url value="/index.jsp"/>'>홈으로</a></li>
						<li><a href='<c:url value="/Board.do?action=write"/>'>게시글 쓰기</a></li>
						<li><a href='<c:url value="/Board.do?action=list"/>'>게시글 목록</a></li>
						<li><a href='<c:url value="/Login.do?action=logout"/>'>로그아웃</a></li>
						<li><a href='<c:url value="/Board.do?action=member"/>'>마이페이지</a></li>
					</ul>
					<br>
					<BR>
					<BR>
					<BR>
					<font size=3 color="white"><B>${name}님 환영합니다.</B></font>
				</c:when>
				<c:when test="${empty userid}">
					<ul id="nav-top" class="nav navbar-nav navbar-right">
						<li><a href='<c:url value="/Member.do?action=insert"/>'>회원가입</a></li>
						<li><a href='<c:url value="/Login.jsp"/>'>로그인</a></li>
					</ul>
				</c:when>
			</c:choose>


		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>