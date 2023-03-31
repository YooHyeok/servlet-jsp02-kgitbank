<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 입력</title>
</head>
<body>
<h2> 개인 정보 입력 </h2>
<form action="/quiz/Info.do" method="post">
<table>

<tr>
<td>아이디 : </td><td><input type=text name=id></td>
</tr>

<tr>
비밀번호 : </td><td><input type=password name=pw></td>
</tr>

<tr>
<td>이름 : </td><td><input type=text name=name></td>
</tr>

<tr>
<td>생년월일 : </td><td><input type=date name=date></td>
</tr>

<tr>
<td>성별 : </td><td><input type=radio name=gender value=M>남</td>
<td>성별 : </td><td><input type=radio name=gender value=F>여
</td>
</tr>

<tr>
<td>취미 : </td><td>
<input type=checkbox name=hobby value=1> 게임
<input type=checkbox name=hobby value=2> 피아노
<input type=checkbox name=hobby value=3> 노래
</td>
</tr>

<tr>
<td>주소 : </td><td><select name=area>
<option value=1>서울
<option value=1>대전
<option value=1>대구
<option value=1>부산
</select>
</td>
</tr>

<tr>
<td>자기소개 : </td><td><textara name=tosay cols=20 rows=5></textara>
</td>
</tr>

<tr>
<td colspan=2 align=center>
<input type=submit value=확인>
<input type=reset value=취소>
</td>
</tr> 
 
</table>
</form>
</body>
</html>