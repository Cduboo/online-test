<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>TEST LIST</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<!-- studentMenu include -->
		<div>
			<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp" />
		</div>
		<!-- main -->
		<div class="container">
			<h1 class="mt-3">시험 관리</h1>
			<c:if test="${empty testList}">
				<div>조회된 시험이 없습니다.</div>
			</c:if>
			<table id="table" class="table table-hover text-center">
				<thead>
					<tr class="table-secondary">
						<th>시험 제목</th>
						<th>시험 내용</th>
						<th>시험 기간</th>
						<th>시험 담당</th>
						<th>시험 응시</th>
					</tr>
				</thead>
				<tbody>
					<jsp:useBean id="now" class="java.util.Date" />
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />
					<c:forEach var="t" items="${testList}">
						<tr>
							<td>${t.testTitle}</td>
							<td>${t.testMemo}</td>
							<td>${t.startDate} ~ ${t.endDate}</td>
							<td>${t.teacherName}</td>
							<td>
								<c:if test="${t.testCk != null}"> <!-- 응시한 시험이면 -->
									<a class="text-danger" href="${pageContext.request.contextPath}/student/test/testResult?testNo=${t.testNo}">시험 결과</a>	
								</c:if>
								<c:if test="${t.testCk == null}">
									<c:if test="${today >= t.startDate && today <= t.endDate}">
										<form action="${pageContext.request.contextPath}/student/test/testDetail" method="post">
											<input type="hidden" name="testNo" value="${t.testNo}">
											<button type="submit">시험 응시</button>
										</form>
									</c:if>
									<c:if test="${today < t.startDate || today > t.endDate}">
										<button type="button" disabled="disabled">시험 응시</button>		
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>