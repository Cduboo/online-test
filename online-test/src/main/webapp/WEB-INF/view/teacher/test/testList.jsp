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
		<!-- teacherMenu include -->
		<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		<!-- main -->
		<div class="container">
			<h1 class="mt-3">시험 관리</h1>
			<c:if test="${empty testList}">
				<div>조회된 내용이 없습니다.</div>
			</c:if>
			<div class="d-flex justify-content-end mb-3">				
				<!-- 시험 등록 modal -->
				<button class="btn btn-primary" class="btn btn-primary" type="button" data-bs-toggle="modal" data-bs-target="#addTest">시험 등록</button>
				<!-- Modal -->
				<div class="modal fade" id="addTest" data-bs-backdrop="static"
					data-bs-keyboard="false" tabindex="-1"
					aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-body">
								<form class="form form-horizontal px-4 mt-5" action="${pageContext.request.contextPath}/teacher/test/addTest" method="post">
									<input type="hidden" name="teacherNo" value="${loginTeacher.teacherNo}">
									<table class="table mt-3">
										<tr>
											<th>시험명</th>
											<td><input class="form-control" type="text" name="testTitle"/></td>
										</tr>
										<tr>
											<th>시험 내용</th>
											<td><textarea class="form-control" rows="5" cols="50" name="testMemo"></textarea></td>
										</tr>
										<tr>
											<th>시험 기간</th>
											<td>
												<input class="form-control" type="datetime-local" name="startDate"/>
												<div class="text-center m-1">~</div> 
												<input class="form-control" type="datetime-local" name="endDate"/>						
											</td>
										</tr>
									</table>
									<div class="d-grid gap-2">
										<button class="btn btn-lg btn-primary" type="submit">등록</button>
										<button class="btn btn-lg btn-secondary" type="button" data-bs-dismiss="modal">취소</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<table id="table" class="table table-hover text-center">
				<thead>
					<tr class="table-secondary">
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
								<a class="text-primary" href="${pageContext.request.contextPath}/teacher/test/testDetail?testNo=${t.testNo}">${t.testTitle}</a>
							</td>
							<td>${t.testMemo}</td>
							<td>${t.startDate} ~ ${t.endDate}</td>
							<td>${t.teacherName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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