<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TESTRESULT</title>
	</head>
	<body>
		<!-- studentMenu include -->
		<div>
			<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp" />
		</div>
		
		<h1>시험 결과</h1>
		<c:forEach var="tr" items="${testResult}">	
			<c:if test="${tr.questionIdx == 1}">
				<div>시험명 : ${tr.testTitle}</div>
			</c:if> 
		</c:forEach>
		<table border="1">
			<tr>
				<th>문제 번호</th>
				<th>정답</th>
				<th>제출답안</th>
				<th>정답여부</th>
			</tr>
			<c:forEach var="tr" items="${testResult}">			
				<tr>
					<td>${tr.questionIdx}</td>
					<td>${tr.exampleIdx}</td>
					<td>${tr.answer}</td>
					<td>
						<c:set var="v" value="${tr.exampleIdx eq tr.answer ? 'O' : 'X' }"></c:set>
						<c:out value="${v}"></c:out>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>