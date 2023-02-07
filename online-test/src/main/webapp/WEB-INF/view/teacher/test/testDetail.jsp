<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TEST DETAIL</title>
	</head>
	<body>
		<!-- teacherMenu include -->
		<div>
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		</div>
		
		<h1>시험 관리</h1>
		<a href="${pageContext.request.contextPath}/teacher/test/removeTest?testNo=${testOne.testNo}">시험 삭제</a>
		<a href="${pageContext.request.contextPath}/teacher/test/modifyTest?testNo=${testOne.testNo}">시험 수정</a>
		<!-- 시험 정보 -->
		<table border="1">
			<thead>
				<tr>
					<th>시험 제목</th>
					<th>시험 내용</th>
					<th>시험 기간</th>
					<th>시험 담당</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${testOne.testTitle}</td>
					<td>${testOne.testMemo}</td>
					<td>${testOne.startDate} ~ ${testOne.endDate}</td>
					<td>${testOne.teacherName}</td>
				</tr>
			</tbody>
		</table>
		 
		<!-- 시험 문제 목록 -->
		<c:if test="${empty questionList}">
			생성된 문제가 없습니다.
		</c:if>
		<c:if test="${not empty questionList}">
			<c:forEach var="q" items="${questionList}" varStatus="status">
				<c:if test="${q.exampleIdx == 1}">
					<div>${q.questionIdx}번 ${q.questionTitle}</div>
				</c:if>
				<div>
					${q.exampleIdx}. ${q.exampleTitle} ${q.exampleOx}
				</div>
			</c:forEach>
		</c:if>
	</body>
</html>