<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MODIFY EMPLOYEE</title>
	</head>
	<body>
		<!-- empMenu include -->
		<div>
			<c:import url="/WEB-INF/view/employee/inc/employeeMenu.jsp" />
		</div>
		
		<h1>비밀번호 수정</h1>
		<span>${errorMsg}</span>
		<form action="${pageContext.request.contextPath}/employee/modifyEmployeePw" method="post">
			<table border="1">
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="oldPw"/></td>
				</tr>
				<tr>
					<td>새 비밀번호</td>
					<td><input type="password" name="newPw"/></td>
				</tr>
			</table>
			<button type="submit">수정</button>
		</form>
	</body>
</html>