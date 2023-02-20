<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Online-test</a>
		<button class="navbar-toggler" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarColor01"
			aria-controls="navbarColor01" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav me-auto">
				<c:if test="${empty loginTeacher}">
					<li class="nav-item">
						<a class="nav-link active" href="${pageContext.request.contextPath}/home">Home
							<span class="visually-hidden">(current)</span>
						</a>
					</li>
				</c:if>
				<c:if test="${not empty loginTeacher}">
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/teacher/test/testList">시험 관리</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/teacher/modifyTeacherPw">비밀번호 수정</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/teacher/logout">로그아웃</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
<!-- 
	 시험회차 관리(CRUD)
	 개별 시험회차 클릭 시 해당 회차의 문제 리스트 출력 (문제 CRUD)
	 개별 문제 클릭 시 해당 문제의 보기 리스트 출력 (보기 CRUD) ... -> 사용자 접근성을 위해 보기 메뉴 추가 
	 question + example table -> 시험지 , paper table -> 답안지
	 
	 그 외 기능 추가...
-->