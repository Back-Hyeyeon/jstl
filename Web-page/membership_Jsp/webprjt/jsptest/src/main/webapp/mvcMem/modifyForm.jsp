<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="member.mdo?cmd=modifyProc" name="regForm">
		<table border="1">
			<tr>
				<td colspan="2" align="center">회원 수정 정보 입력</td>
			</tr>
			<tr>
				<td align="right">아이디 :</td>
				<td><input type="hidden" name="id" value="${id}" /> <c:out
						value="${id}" /></td>
			</tr>
			<tr>
				<td align="right">패스워드 :</td>
				<td><input type="password" name="pass" value="${pass}" /></td>
			</tr>
			<tr>
				<td align="right">패스워드 확인 :</td>
				<td><input type="password" name="repass" value="${pass}" /></td>
			</tr>
			<tr>
				<td align="right">이름 :</td>
				<td><input type="hidden" name="name" value="${name}" /> <c:out
						value="${name}" /></td>
			</tr>
			<tr>
				<td align="right">전화번호 :</td>
				<td><select name="phone1">
						<option value="02"
							${phone1 eq '02' ? "selected='selected'" : 'null'}>02</option>
						<option value="010"
							${phone1 eq '010' ? "selected='selected'" : 'null'}>010</option>
						<option value="016"
							${phone1 eq '016' ? "selected='selected'" : 'null'}>016</option>
						<option value="017"
							${phone1 eq '017' ? "selected='selected'" : 'null'}>017</option>
						<option value="018"
							${phone1 eq '018' ? "selected='selected'" : 'null'}>018</option>
						<option value="019"
							${phone1 eq '019' ? "selected='selected'" : 'null'}>019</option>
				</select>&nbsp;-&nbsp; <input type="text" name="phone2" size="5"
					value="${phone2}" /> &nbsp;-&nbsp; <input type="text" name="phone3"
					size="5" value="${phone3}" /></td>
			</tr>
			<tr>
				<td align="right">이메일 :</td>
				<td><input type="text" name="email" value="${email}" /></td>
			</tr>
			<tr>
				<td align="right">우편번호 :</td>
				<td><input type="text" name="zipcode" size="20"
					value="${zipcode}" />&nbsp; <input type="button" value="찾기"
					onClick="zipCheck()" /></td>
			</tr>
			<tr>
				<td align="right">주소1 :</td>
				<td><input type="text" name="address1" size="50"
					value="${address1}" /></td>
			</tr>
			<tr>
				<td align="right">주소2 :</td>
				<td><input type="text" name="address2" size="30"
					value="${address2}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="submit">&nbsp;&nbsp; 
				<input type="button"value="정보수정" onClick="inputCheck()" />&nbsp;&nbsp; 
				<input
					type="button" value="취소"
					onclick="javascript:window.location='member.mdo?cmd=login'" /></td>
			</tr>
		</table>
	</form>
</body>
</html>