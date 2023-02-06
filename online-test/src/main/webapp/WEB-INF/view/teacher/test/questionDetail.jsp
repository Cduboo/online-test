<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>QUESTION DETAIL</title>
	</head>
	<body>
		<!-- teacherMenu include -->
		<div>
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		</div>
		
		<table border="1">
			<thead>
				<tr>
					<th>보기 번호</th>
					<th>보기 내용</th>
					<th>정답</th>
				</tr>
			</thead>
			<c:forEach var="q" items="${exampleListByQuestion}">
			<tbody>
				<tr>
					<td>${q.exampleIdx}</td>
					<td>${q.exampleTitle}</td>
					<td>${q.exampleOx}</td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
	</body>
</html>