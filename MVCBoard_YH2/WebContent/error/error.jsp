<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
</head>
<body>
<script type="text/javascript">
alert("${message}");
history.back();
<!--특정 메시지를 띄울때 사용하는 에러페이지-->

</script>
</body>
</html>