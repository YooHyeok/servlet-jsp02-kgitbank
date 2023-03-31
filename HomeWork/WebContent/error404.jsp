<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
</head>
<body>
<%--자바스크립트 --%>
<script type="text/javascript">
alert("찾으시는 페이지가 없습니다.");
location.back();
</script>

<h1>찾으시는 페이지가 없습니다.</h1>
<a href="<%=request.getHeader("referer") %>"></a><input type=button value=뒤로가기></a>
</body>
</html>