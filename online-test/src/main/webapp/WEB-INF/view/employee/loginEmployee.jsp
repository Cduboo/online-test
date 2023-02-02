<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOGIN EMPLOYEE</title>
	</head>
	<body>
		<!-- 로그인 전 -->
		<c:if test="${empty loginEmployee}">
			<h1>직원 로그인</h1>
			<form action="${pageContext.request.contextPath}/loginEmployee" method="post">
				<table border="1">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="employeeId"/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="employeePw"/></td>
					</tr>
				</table>
				<button type="submit">로그인</button>
			</form>		
		</c:if>
		<!-- 로그인 후 -->
		<c:if test="${not empty loginEmployee}">
			<!-- empMenu include -->
			<div>
				<c:import url="/WEB-INF/view/employee/inc/employeeMenu.jsp" />
			</div>
			<span>${loginEmployee.employeeName}님 반갑습니다.</span>
			<a href="${pageContext.request.contextPath}/employee/logout">로그아웃</a>
		</c:if>
	</body>
</html>