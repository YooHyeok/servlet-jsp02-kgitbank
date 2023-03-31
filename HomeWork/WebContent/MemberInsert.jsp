<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 입력</title>
</head>
<body>
<fieldset>
<form action="/hw/Member.do" method=post>
<legend>정보 입력</legend>
<table>
<tr><td>아이디</td><td><input type=text name=id></td></tr>
<tr><td>비밀번호</td><td><input type=password name=pw></td></tr>
<tr><td>이름</td><td><input type=name name=name></td></tr>
<tr><td>성별</td><td>
<input type=radio name=gender value=F>여
<input type=radio name=gender value=M>남
</td>
</tr>
<tr><td>생년월일</td><td><input type=date name=birth></td></tr>

<tr>
<td>취미</td>
<td>
<input type=checkbox name=hobby value=1>게임
<input type=checkbox name=hobby value=2>피아노
<input type=checkbox name=hobby value=3>노래
</td>
</tr>
<tr><td>지역</td><td>
<select name=area>
<option value=1>서울
<option value=2>대전
<option value=3>대구
<option value=4>부산
<option value=5>인천
</select>

<tr><td>자기소개</td><td><textarea name=introduce cols=22 row=3>자기소개를 써주세요.</textarea>
</td></tr>

<tr><td colspan=2>
<input type=submit value=저장>
<input type=reset value=취소>
</td></tr>

</table>
</form>
</fieldset>
</body>
</html>