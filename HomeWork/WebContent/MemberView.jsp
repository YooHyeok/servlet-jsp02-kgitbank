<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="lab.web.vo.MemberVO" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뷰 페이지</title>
</head>
<body>
<a href="/hw/Member.do?action=list"><input type=button value=목록조회></a>
<a href="/hw/MemberInsert.jsp"><input type=button value=정보입력></a>
<a href="/hw/Login.do"><input type=button value=로그아웃></a>
<%MemberVO mem = (MemberVO)request.getAttribute("mem"); %>

<%HashMap<String,String> map = (HashMap<String,String>)
request.getAttribute("map");%>

<%HashMap<String,String> map2 = (HashMap<String,String>)
request.getAttribute("map2");%>
<table>
<tr><td>아이디</td><td><%=mem.getId() %></td></tr>
<tr><td>비밀번호</td><td><%=mem.getPw() %></td></tr>
<tr><td>이름</td><td><%=mem.getName() %></td></tr>
<tr><td>성별</td><td><%=mem.getGender()=='M' ? "남" : "여" %></td></tr>

<tr><td>생년월일</td><td>
<%=mem.getBirth().getYear()+1900 %>년
<%=mem.getBirth().getMonth()+1%>월
<%=mem.getBirth().getDate()%>일
</td></tr>
<tr><td>취미</td></tr>
<%if(mem.getHobby().size()==0) {%>
취미없음
<%} else{ %>
<%for(String key : mem.getHobby()){ %>
<%=map.get(key) %>&nbsp;
<%}} %>
<tr><td>지역</td><td>
<%=map2.get(mem.getArea()) %></td></tr> <%--mem.getArea() => 키값 --%>
<tr><td>자기소개</td><th>
<%=mem.getIntroduce().replaceAll("\n", "<br>") %></th></tr>
<%--replaceAll("\n", "<br>") : \n=><br>로 바꿈 --%>



</table>
</body>
</html>