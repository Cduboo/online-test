<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>	
	
	<a href="${pageContext.request.contextPath}/teacher/teacherTestList">시험 관리</a>
	<!-- 
		 시험회차 관리(CRUD)
		 개별 시험회차 클릭 시 해당 회차의 문제 리스트 출력 (문제 CRUD)
		 개별 문제 클릭 시 해당 문제의 보기 리스트 출력 (보기 CRUD) ... -> 사용자 접근성을 위해 보기 메뉴 추가 
		 question + example table -> 시험지 , paper table -> 답안지
		 
		 그 외 기능 추가...
	 -->
	<a href="${pageContext.request.contextPath}/teacher/modifyTeacherPw">비밀번호 수정</a>
	
	
	<a href="${pageContext.request.contextPath}/teacher/logout">로그아웃</a>

</div>