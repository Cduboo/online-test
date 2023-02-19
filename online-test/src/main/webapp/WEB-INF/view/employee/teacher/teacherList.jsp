<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>TEACHER LIST</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<!-- menu -->
		<c:import url="/WEB-INF/view/employee/inc/employeeMenu.jsp" />
		<div class="container">
			<h1 class="mt-3">강사 목록</h1>
			<div class="d-flex justify-content-end mb-3">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/employee/teacher/addTeacher">강사 등록</a>
			</div>
			<form id="rvForm" action="${pageContext.request.contextPath}/employee/teacher/removeTeacher" method="post">
				<input type="hidden" name="teacherNo" value="0">
				<table id="table" class="table table-hover text-center">
					<thead>
						<tr class="table-secondary">
							<th>아이디</th>
							<th>이름</th>
							<th style="width: 10%">삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="t" items="${teacherList}">
							<tr>
								<td>${t.teacherId}</td>
								<td>${t.teacherName}</td>
								<td>
									<button class="btn btn-sm btn-primary rvBtn" type="button" value="${t.teacherNo}">삭제</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<!-- 페이징 -->
			<div class="d-flex justify-content-center">
				<ul class="pagination">
					<li class="page-item">
						<a class="page-link" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=1&searchWord=${searchWord}">&laquo;</a>
					</li>
					<c:if test="${endPage > rowPerPage}">
						<li class="page-item">
							<a class="page-link" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${startPage-rowPerPage}&searchWord=${searchWord}">&lt;</a>
						</li>
					</c:if>
					<!-- 페이지 번호 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
						<li class="page-item">
							<c:if test="${i <= lastPage}">
								<c:if test="${currentPage == i}">
									<a class="page-link active" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
								</c:if>
								<c:if test="${currentPage != i}">
									<a class="page-link" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
								</c:if>
							</c:if>
						</li>
					</c:forEach>
					<li class="page-item">
						<!-- 다음 > -->
						<c:if test="${endPage < lastPage}">
							<a class="page-link" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${startPage+rowPerPage}&searchWord=${searchWord}">&gt;</a>
						</c:if>
					</li>
					<li class="page-item">
						<!-- 마지막 페이지 >> -->
						<a class="page-link" href="${pageContext.request.contextPath}/employee/teacher/teacherList?currentPage=${lastPage}&searchWord=${searchWord}">&raquo;</a>
					</li>
				</ul>
			</div>
			<!-- 검색 -->
			<div class="container w-50">
				<form action="${pageContext.request.contextPath}/employee/teacher/teacherList" method="get">
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="searchWord" placeholder="강사 이름..." aria-label="강사 이름..." aria-describedby="searchBtn">
						<button class="btn btn-primary" type="button" id="searchBtn">검색</button>
					</div>
				</form>
			</div>
		</div>
		
		<script>
			$(function() {
				
				let msg = '${msg}';
				if(msg == 'REMOVE_ERROR') {
					alert('해당 강사는 등록한 시험이 존재합니다.');
				}
				
				$('.rvBtn').click(function() {
					let ans = confirm('삭제 하시겠습니까?');
					if(ans) {
						$('input[name=teacherNo]').val($(this).val());
						$('#rvForm').submit();
					}
				});
				
				$('input[name=searchWord]').keyup(function() {
					$.ajax({
						url : '${pageContext.request.contextPath}/employee/searchTeacherList',
						type : 'get',
						data : {searchWord : $('input[name=searchWord]').val()},
						success : function(data) {
							$('#table > tbody').empty();
							if(data.length >= 1) {
								data.forEach(function(item) {
									str = '<tr>'
									str += '<td>' + item.teacherId + '</td>'
									str += '<td>' + item.teacherName + '</td>'
									str += '<td><a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/employee/teacher/removeTeacher?teacherNo='+item.teacherNo+'">삭제</a></td>'
									str += '</tr>'
									$('#table').append(str);
								});
							}
						}
					});
				});
				
			});
		</script>
	</body>
</html>