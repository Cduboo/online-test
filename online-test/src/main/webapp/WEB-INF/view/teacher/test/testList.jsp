<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TEST LIST</title>
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	</head>
	<body>
		<!-- teacherMenu include -->
		<div>
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		</div>
		
		<h1>시험 관리</h1>
		<a href="${pageContext.request.contextPath}/teacher/test/addTest">시험 등록</a>
		
		<c:if test="${empty testList}">
			<div>조회된 내용이 없습니다.</div>
		</c:if>
		
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
				<c:forEach var="t" items="${testList}">
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/teacher/test/testDetail?testNo=${t.testNo}">${t.testTitle}</a>
						</td>
						<td>${t.testMemo}</td>
						<td>${t.startDate} ~ ${t.endDate}</td>
						<td>${t.teacherName}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%-- <jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />
		<c:if test="${today <  '2023-02-03 23:40:00'}">
			더 큼
		</c:if> --%>
		<script>
			$(function() {
				let msg = '${msg}';
				if(msg == 'ADD_SUCCESS') {
					alert('시험 등록 성공')
				}
				if(msg == 'ADD_ERROR') {
					alert('시험 등록 실패')
				}
			});
		</script>
	</body>
</html>