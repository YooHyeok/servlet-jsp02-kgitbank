<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 조회 페이지</title>
</head>
<body>
<%if(session.getAttribute("userid")!=null) {%>
<%=session.getId()%><br>
<a href="/quiz/LoginInfo.do"><input type=button value="목록조회"></a>
<a href="/quiz/LoginInfo/InputInfo.jsp"><input type=button value="정보조회"></a>
<a href="/quiz/Login.do"><input type=button value="로그아웃"></a>
<!-- 하이퍼링크를 버튼으로 만드는 폼 -->
<!-- <a href="/quiz/Example.do"><input type=button value="목록 조회">  -->
<%}else{ response.sendRedirect("/quiz/LoginInfo/Login.jsp"); } %>
</body>
</html>

