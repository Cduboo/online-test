<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOGIN STUDENT</title>
	</head>
	<body>
		<!-- 로그인 전 -->
		<c:if test="${empty loginStudent}">
			<h1>학생 로그인</h1>
			<form action="${pageContext.request.contextPath}/loginStudent" method="post">
				<table border="1">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="studentId"/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="studentPw"/></td>
					</tr>
				</table>
				<button type="submit">로그인</button>
			</form>		
		</c:if>
	</body>
</html>