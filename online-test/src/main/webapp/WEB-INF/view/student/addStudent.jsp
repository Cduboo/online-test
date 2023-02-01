<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>STUDENT ADD</title>
	</head>
	<body>
		<!-- empMenu include -->
		<div>
			<c:import url="/WEB-INF/view/employee/inc/employeeMenu.jsp" />
		</div>
		
		<h1>학생 등록</h1>
		<span>${errorMsg}</span>
		<form action="${pageContext.request.contextPath}/student/addStudent" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="studentId"/></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="studentName"/></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="studentPw"/></td>
				</tr>
			</table>
			<button type="submit">등록</button>
		</form>
	</body>
</html>