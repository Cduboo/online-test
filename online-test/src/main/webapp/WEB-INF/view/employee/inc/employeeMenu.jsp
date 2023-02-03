<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>	
	<!-- 사원 등록 시 ID체크(employee + teacher + student) -->
	<a href="${pageContext.request.contextPath}/employee/employeeList">직원 관리</a>
	
	<!-- 강사 목록, 삭제 -->
	<a href="${pageContext.request.contextPath}/employee/teacher/teacherList">강사 관리</a>
	
	<!-- 학생 목록, 삭제 -->
	<a href="${pageContext.request.contextPath}/employee/student/studentList">학생 관리</a>
	
	<a href="${pageContext.request.contextPath}/employee/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath}/employee/modifyEmployeePw">비밀번호 수정</a>
</div>