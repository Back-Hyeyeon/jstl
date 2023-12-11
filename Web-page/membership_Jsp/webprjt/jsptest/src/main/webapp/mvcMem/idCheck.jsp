<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#FFFFCC">
	<br>
	<div>
		<b>${id}</b>
		<c:if test="${check eq true}">
는 이미 존재하는 ID입니다.<br />
		</c:if>
		<c:if test="${check ne true}">는 사용 가능 합니다.<br />
		</c:if>
		<a href="#" onClick="javascript:self.close()">닫기</a>
	</div>
</body>
</html>