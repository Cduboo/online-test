<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOGIN EMPLOYEE</title>
	</head>
	<body>
		<h1>직원 로그인</h1>
		<form action="${pageContext.request.contextPath}/employee/loginEmployee" method="post">
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
	</body>
</html>