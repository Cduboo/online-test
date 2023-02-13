<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>TESTRESULT</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
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
			<c:set var="i" value="0"/>			
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
				<c:set var="i" value="${i = i+1}"/>
			</c:forEach>
		</table>
		<div>내 점수 : ${100 / i eq 'Infinity' ? '' : 100/i}점</div>
	</body>
</html>