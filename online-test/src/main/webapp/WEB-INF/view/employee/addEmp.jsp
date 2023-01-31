<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EMP ADD</title>
	</head>
	<body>
		<h1>직원 등록</h1>
		<form action="${pageContext.request.contextPath}/employee/addEmp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="empId"/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="empName"/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="empPw"/></td>
				</tr>
			</table>
			<button type="submit">직원 등록</button>
		</form>
	</body>
</html>