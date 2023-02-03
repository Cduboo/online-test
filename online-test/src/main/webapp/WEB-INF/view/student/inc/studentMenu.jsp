<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>	
	<!-- 
		 지난 리스트 + 점수 - 점수 확인 버튼(회차별 답안지(OX) 확인)
		 오늘 날짜 시험 리스트는 응시 버튼 활성화 그 외 비활성화
		 응시 버튼 클릭 시 시험지 출력 : 시험 문제(문제 question, 보기 example)와 답안지(paper) 제출 버튼
		 question + example table -> 시험지 , paper table -> 답안지
		 
		 그 외 본인 등수, 제출 현황 등    
	 -->
	<a href="${pageContext.request.contextPath}/student/studentTestList">시험 관리</a>
	<a href="${pageContext.request.contextPath}/student/modifyStudentPw">비밀번호 수정</a>
	<a href="${pageContext.request.contextPath}/student/logout">로그아웃</a>
</div>