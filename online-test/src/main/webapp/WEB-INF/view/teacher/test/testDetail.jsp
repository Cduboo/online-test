<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TEST DETAIL</title>
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<!-- teacherMenu include -->
		<div>
			<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		</div>
		
		<h1>시험 관리</h1>
		<c:if test="${empty questionList}">
			<a href="${pageContext.request.contextPath}/teacher/test/removeTest?testNo=${testOne.testNo}">시험 삭제</a>
		</c:if>
		<c:if test="${not empty questionList}">
			<a href="#" onclick="alert('해당 시험에 등록된 문제를 모두 삭제해주세요.')">시험 삭제</a>
		</c:if>
		<a href="${pageContext.request.contextPath}/teacher/test/modifyTest?testNo=${testOne.testNo}">시험 수정</a>
		<!-- 시험 정보 -->
		<table border="1">
			<thead>
				<tr>
					<th>시험 제목</th>
					<th>시험 내용</th>
					<th>시험 기간</th>
					<th>시험 담당</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${testOne.testTitle}</td>
					<td>${testOne.testMemo}</td>
					<td>${testOne.startDate} ~ ${testOne.endDate}</td>
					<td>${testOne.teacherName}</td>
				</tr>
			</tbody>
		</table>
		<!-- Button trigger modal -->
		<button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">문제 등록</button>
		<a class="btn" href="${pageContext.request.contextPath}/teacher/test/modifyQuetion">문제 관리</a>
		<!-- Modal -->
		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-body">
						<form action="${pageContext.request.contextPath}/teacher/test/addQuestion" method="post">
							<!-- 문제 -->
							<table border="1">
								<tr>
									<td>
										<input type="hidden" name="testNo" value="${testOne.testNo}"/>
										문제 번호 <input type="number" name="questionIdx"/>
									</td>
								</tr>
								<tr>
									<td>문제 설명</td>
								</tr>
								<tr>
									<td>
										<textarea rows="5" cols="30" name="questionTitle"></textarea>
									</td>
								</tr>
							</table>	
							<!-- 보기(객관식) -->
							<table border="1">
								<tr>
									<th>번호</th>
									<th>보기</th>
									<th>정답여부</th>
								</tr>
								<tr>
									<td><input type="hidden" name="exampleIdx" value="1">보기 1</td>
									<td><input type="text" name="exampleTitle"/></td>
									<td><input type="radio" name="exampleOx" value="0"/></td>
								</tr>
								<tr>
									<td><input type="hidden" name="exampleIdx" value="2">보기 2</td>
									<td><input type="text" name="exampleTitle"/></td>
									<td><input type="radio" name="exampleOx" value="1"/></td>
								</tr>
								<tr>
									<td><input type="hidden" name="exampleIdx" value="3">보기 3</td>
									<td><input type="text" name="exampleTitle"/></td>
									<td><input type="radio" name="exampleOx" value="2"/></td>
								</tr>
								<tr>
									<td><input type="hidden" name="exampleIdx" value="4">보기 4</td>
									<td><input type="text" name="exampleTitle"/></td>
									<td><input type="radio" name="exampleOx" value="3"></td>
								</tr>
							</table>
							<button type="submit">등록</button>
							<button type="button" data-bs-dismiss="modal">취소</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 시험 문제 목록 -->
		<c:if test="${empty questionList}">
			생성된 문제가 없습니다.
		</c:if>
		<c:if test="${not empty questionList}">
			<c:forEach var="q" items="${questionList}">
				<c:if test="${q.exampleIdx == 1 || q.exampleIdx == null}">
					<form action="${pageContext.request.contextPath}/teacher/test/removeQuestion" method="post">
						${q.questionIdx}번 ${q.questionTitle}
						<input type="hidden" name="testNo" value="${testOne.testNo}"/>
						<input type="hidden" name="questionNo" value="${q.questionNo}">
						<c:if test="${q.exampleIdx == null}">
							<button type="submit">문제삭제</button>
						</c:if>
						<c:if test="${q.exampleIdx != null}">
							<button type="button" onclick="alert('해당 문제의 보기를 모두 삭제해주세요.')">문제삭제</button>
						</c:if>
					</form>
				</c:if>
				<div>
					<div>
						<c:if test="${q.exampleIdx != null}">
							<form action="${pageContext.request.contextPath}/teacher/test/removeExample" method="post">
								${q.exampleIdx}. ${q.exampleTitle} ${q.exampleOx}
								<input type="hidden" name="testNo" value="${testOne.testNo}"/>
								<input type="hidden" name="exampleNo" value="${q.exampleNo}">
								<button type="submit">X</button>						
							</form>
						</c:if>
						<c:if test="${q.exampleIdx == null}">
							등록된 보기가 없습니다.
						</c:if>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</body>
</html>