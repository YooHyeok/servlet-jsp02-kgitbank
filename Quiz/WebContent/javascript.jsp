<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("btnHello").onclick=function(){
		document.getElementById("show").innerText = "안녕하신가";
	}
}

</script>
</head>
<body>
<button id="btnHello">인사하기(click)</button>
<div id="show">출력장소1</div>
</body>
</html>