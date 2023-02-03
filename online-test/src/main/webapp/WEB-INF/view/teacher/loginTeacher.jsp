<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>LOGIN TEACHER</title>
	</head>
	<body>
		<!-- 로그인 전 -->
		<c:if test="${empty loginTeacher}">
			<h1>강사 로그인</h1>
			<form action="${pageContext.request.contextPath}/loginTeacher" method="post">
				<table border="1">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="teacherId"/></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="teacherPw"/></td>
					</tr>
				</table>
				<button type="submit">로그인</button>
			</form>		
		</c:if>
		<!-- 로그인 후 -->
		<c:if test="${not empty loginTeacher}">
			<!-- teacherMenu include -->
			<div>
				<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
			</div>
			<span>${loginTeacher.teacherName}님 반갑습니다.</span>
			<a href="${pageContext.request.contextPath}/teacher/logout">로그아웃</a>
		</c:if>
	</body>
</html>