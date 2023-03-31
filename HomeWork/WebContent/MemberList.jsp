<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="lab.web.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
<%if(session.getAttribute("userid")!=null) { %>

<%ArrayList<MemberVO> list = (ArrayList<MemberVO>)request.getAttribute("list"); %>
<a href="/hw/MemberInsert.jsp">정보입력</a>
<a href="/hw/Login.do">로그아웃</a>

<table>
<%for(MemberVO mem : list){ %>
<tr>
<td><a href="/hw/Member.do?action=view&id=<%=mem.getId()%>" ><%=mem.getId() %></a></td>
<%-- 한명을 보겠다. ?action=view => action 이라는이름의 파라미터를 view값으로 보냄. 사용자가 눌렀을때만 됨. --%>
<%-- &뒤에 식별할수있는 수단--%>
<td><%=mem.getName() %></td>
<td><%=mem.getGender()=='M' ? "남" : "여"%></td> 
<%-- M인게 True면 "남" false면 "여"--%>

</tr>
<%} %>
<%}else {response.sendRedirect("/hw/Login.jsp");}%>
</table>
</body>
</html>