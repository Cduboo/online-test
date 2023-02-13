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
				<c:if test="${empty loginStudent}">
					<li class="nav-item">
						<a class="nav-link active" href="${pageContext.request.contextPath}/home">Home
							<span class="visually-hidden">(current)</span>
						</a>
					</li>
				</c:if>
				<c:if test="${not empty loginStudent}">
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/student/test/testList">시험 관리</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/student/modifyStudentPw">비밀번호 수정</a></li>
					<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/student/logout">로그아웃</a></li>
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
<!-- 
	 지난 리스트 + 점수 - 점수 확인 버튼(회차별 답안지(OX) 확인)
	 오늘 날짜 시험 리스트는 응시 버튼 활성화 그 외 비활성화
	 응시 버튼 클릭 시 시험지 출력 : 시험 문제(문제 question, 보기 example)와 답안지(paper) 제출 버튼
	 question + example table -> 시험지 , paper table -> 답안지
	 
	 그 외 본인 등수, 제출 현황 등    
 -->
