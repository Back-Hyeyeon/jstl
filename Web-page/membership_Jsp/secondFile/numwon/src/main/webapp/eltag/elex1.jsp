<%@page import="actiontag.Customer"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Customer customer = new Customer();
	customer.setName("손오공");
	customer.setEmail("son@hamail.net");
	customer.setPhone("010-1234-5678");
	
	request.setAttribute("customer", customer);
	
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("name", "소나타");
	map.put("maker", "현대자동차");
	
	request.setAttribute("car", map);
	
	HashMap<String, String> car = (HashMap<String, String>)request.getAttribute("car");
	String name =car.get("name");
	String maker =car.get("maker");
	/* 위 3가지는 익스프레션 랭귀지 방식이 아닌 것이다 예제로 쓰였고 값을 받는 것은 43번 44번에 있다. */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>이름 : ${customer.name}</li>
		<li>메일 : ${customer.email}</li>
		<li>전화 : ${customer.phone}</li><!-- 익스프레션랭귀지 -->
		 <li>전화 : <%= customer.getPhone() %></li> 
	</ul>
	<br></br>
	<ul>
		<li>자동차 : ${car.name}</li><!-- 도트 연산자의 오른쪽은 반드시 프로퍼티여야한다. -->
		<li>제조사 : ${car.maker}</li>
		<br>
		<li>자동차 : <%= name %></li>
		<li>제조사 : <%= maker%></li>
	</ul>
</body>
</html>