<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EMPLOYEE LIST</title>
	</head>
	<body>
		<!-- empMenu include -->
		<div>
			<c:import url="/WEB-INF/view/employee/inc/employeeMenu.jsp" />
		</div>
		
		<h1>직원 목록 ${employee.employeeId}</h1>
		<a href="${pageContext.request.contextPath}/employee/addEmployee">직원 등록</a>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="e" items="${employeeList}">
				<tr>
					<td>${e.employeeId}</td>
					<td>${e.employeeName}</td>
					<td><a href="${pageContext.request.contextPath}/employee/removeEmployee?employeeNo=${e.employeeNo}">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
		<!-- 검색 -->
		<form action="${pageContext.request.contextPath}/employee/employeeList" method="get">
			<input type="text" name="searchWord" placeholder="직원 이름..."/>
			<button type="submit">이름 검색</button>
		</form>
		<!-- 페이징 -->
		<div>
			<!-- 첫 페이지 << -->
			<a href="${pageContext.request.contextPath}/employee/employeeList?currentPage=1&searchWord=${searchWord}">&lt;&lt;</a>
			<!-- 이전 < -->
			<c:if test="${endPage > rowPerPage}">
				<a href="${pageContext.request.contextPath}/employee/employeeList?currentPage=${startPage-rowPerPage}&searchWord=${searchWord}">&lt;</a>
			</c:if>
			<!-- 페이지 번호 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<c:if test="${i <= lastPage}">
					<c:if test="${currentPage == i}">
						<a href="${pageContext.request.contextPath}/employee/employeeList?currentPage=${i}&searchWord=${searchWord}" style="color: red;">${i}</a>
					</c:if>
					<c:if test="${currentPage != i}">
						<a href="${pageContext.request.contextPath}/employee/employeeList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
					</c:if>
				</c:if>
			</c:forEach>
			<!-- 다음 > -->
			<c:if test="${endPage < lastPage}">
				<a href="${pageContext.request.contextPath}/employee/employeeList?currentPage=${startPage+rowPerPage}&searchWord=${searchWord}">&gt;</a>
			</c:if>
			<!-- 마지막 페이지 >> -->
			<a href="${pageContext.request.contextPath}/employee/employeeList?currentPage=${lastPage}&searchWord=${searchWord}">&gt;&gt;</a>
		</div>
	</body>
</html>