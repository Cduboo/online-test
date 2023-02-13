<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/employee/employeeMain">Online-test</a>
		<button class="navbar-toggler" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarColor01"
			aria-controls="navbarColor01" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav me-auto">
				<c:if test="${empty loginEmployee}">
					<li class="nav-item">
						<a class="nav-link active" href="${pageContext.request.contextPath}/home">Home
							<span class="visually-hidden">(current)</span>
						</a>
					</li>
				</c:if>
				<c:if test="${not empty loginEmployee}">
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/employeeList">직원 관리</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/teacher/teacherList">강사 관리</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/student/studentList">학생 관리</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/modifyEmployeePw">비밀번호 수정</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/employee/logout">로그아웃</a></li>
				</c:if>
			</ul>
			<form class="d-flex">
				<input class="form-control me-sm-2" type="search"
					placeholder="Search">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>