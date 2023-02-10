<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ADD TEST</title>
	</head>
	<body>
		<!-- teacherMenu include -->
		<div>
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		</div>
		
		<h1>시험 관리</h1>		
		<form action="${pageContext.request.contextPath}/teacher/test/addTest" method="post">
			<input type="hidden" name="teacherNo" value="${loginTeacher.teacherNo}">
			<h3>시험 정보 등록</h3>
			<table border="1">
				<tr>
					<th>시험명</th>
					<td><input type="text" name="testTitle"/></td>
				</tr>
				<tr>
					<th>시험 내용</th>
					<td><textarea rows="5" cols="50" name="testMemo"></textarea></td>
				</tr>
				<tr>
					<th>시험 기간</th>
					<td>
						<input type="datetime-local" name="startDate"/> ~ 
						<input type="datetime-local" name="endDate"/>						
					</td>
				</tr>
			</table>
			<button type="submit">시험 등록</button>
		</form>
	</body>
</html>