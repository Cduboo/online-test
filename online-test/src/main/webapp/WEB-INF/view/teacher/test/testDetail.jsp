<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>TEST DETAIL</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<jsp:useBean id="now" class="java.util.Date" />
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today" />
		<!-- teacherMenu include -->
		<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		<!-- main -->
		<div class="container">
			<h1 class="mt-3">시험 관리</h1>
			<div class="d-flex justify-content-end mb-3">
				<c:if test="${empty MOD}">
					<form action="${pageContext.request.contextPath}/teacher/test/modifyTest" method="get">
						<input type="hidden" name="testNo" value="${testOne.testNo}">
						<c:if test="${today >= testOne.startDate && today <= testOne.endDate}">
							<button class="btn btn-primary" type="button" disabled="disabled">시험 수정</button>
						</c:if>
						
						<c:if test="${today < testOne.startDate || today > testOne.endDate}">			
							<c:if test="${testOne.paperCountByTest > 0}">
								<button class="btn btn-primary" type="button" disabled="disabled">시험 수정</button>
							</c:if>
							<c:if test="${testOne.paperCountByTest == 0}">
								<button class="btn btn-primary" type="submit">시험 수정</button> 
							</c:if>
						</c:if>
					</form>
				</c:if>
				<c:if test="${empty questionList}">
					<form action="${pageContext.request.contextPath}/teacher/test/removeTest" method="post">
						<input type="hidden" name="testNo" value="${testOne.testNo}">
						<button class="btn btn-secondary ms-1" type="submit">시험 삭제</button>
					</form>				
				</c:if>
				<c:if test="${not empty questionList}">
					<button class="btn btn-secondary ms-1" type="button" id="deleteTestBtn" onclick="alert('해당 시험에 등록된 문제를 모두 삭제해주세요.')">시험 삭제</button>
				</c:if>
			</div>
			<!-- 시험 수정 -->
			<c:if test="${empty MOD}">
				<table class="table table-hover text-center">
					<thead>
						<tr class="table-secondary">
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
			</c:if>
		</div>
		
		<c:if test="${MOD eq 'MOD_MODIFY'}">
			<div class="container">
				<form action="${pageContext.request.contextPath}/teacher/test/modifyTest" method="post">			
					<input type="hidden" name="testNo" value="${testOne.testNo}">
					<h3>시험 정보 등록</h3>
					<table class="table table-bordered text-center align-middle">
						<tr>
							<th class="text-bg-light">시험명</th>
							<td><input class="form-control form-control-sm" type="text" name="testTitle" value="${testOne.testTitle}"/></td>
						</tr>
						<tr>
							<th class="text-bg-light">시험 내용</th>
							<td><textarea class="form-control form-control-sm" rows="5" cols="50" name="testMemo">${testOne.testMemo}</textarea></td>
						</tr>
						<tr>
							<th class="text-bg-light">시험 기간</th>
							<td>
								<input class="form-control form-control-sm" type="datetime-local" name="startDate" value="${testOne.startDate}"/>
								<div class="text-center">~</div> 
								<input class="form-control form-control-sm" type="datetime-local" name="endDate" value="${testOne.endDate}"/>						
							</td>
						</tr>
					</table>
					<c:if test="${today >= testOne.startDate && today <= testOne.endDate}">
						<div class="d-grid gap-2">
							<button class="btn btn-lg btn-primary" type="button" disabled="disabled">시험 수정</button>
						</div>
					</c:if>
					
					<c:if test="${today < testOne.startDate || today > testOne.endDate}">			
						<c:if test="${testOne.paperCountByTest > 0}">
							<div class="d-grid gap-2">
								<button class="btn btn-lg btn-primary" type="button" disabled="disabled">시험 수정</button>
							</div>
						</c:if>
						<c:if test="${testOne.paperCountByTest == 0}">
							<div class="d-grid gap-2">
								<button class="btn btn-lg btn-primary" type="submit">시험 수정</button> 
							</div>
						</c:if>
					</c:if>
				</form>
			</div>
		</c:if>
		<div class="d-flex justify-content-center">
			<!-- 문제 등록 modal -->
			<button type="button" class="btn btn-sm btn-primary mt-5" data-bs-toggle="modal" data-bs-target="#addQuestion">문제 등록</button>
			<!-- Modal -->
			<div class="modal fade" id="addQuestion" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content rounded-0">
						<div class="modal-body container">
							<div class="clearfix shadow text-white bg-primary p-2">
								<span class="float-start">문제 관리</span>
								<span class="float-end"><button type="button" class="btn-close" data-bs-dismiss="modal"></button></span>
							</div>
							<form class="form form-horizontal p-4" action="${pageContext.request.contextPath}/teacher/test/addQuestion" method="post">
								<!-- 문제 -->
								<table class="table table-bordered">
									<tr>
										<td class="text-bg-light">문제 번호</td>
										<td>
											<input class="form-control" type="hidden" name="testNo" value="${testOne.testNo}"/>
											<input class="form-control form-control-sm" type="number" name="questionIdx" required="required"/>
										</td>
									</tr>
									<tr>
										<td class="text-bg-light">문제 설명</td>
										<td>
											<textarea class="form-control" rows="5" cols="30" name="questionTitle" required="required"></textarea>
										</td>
									</tr>
								</table>	
								<!-- 보기(객관식) -->
								<table class="table table-bordered table-hover mt-3">
									<tr>
										<th class="text-bg-light text-center">번호</th>
										<th class="text-bg-light text-center">보기</th>
										<th class="text-bg-light text-center">정답여부</th>
									</tr>
									<tr>
										<td class="text-bg-light"><input class="form-control" type="hidden" name="exampleIdx" value="1">보기 1</td>
										<td><input class="form-control form-control-sm" type="text" name="exampleTitle" required="required"/></td>
										<td><input class="form-check-input" type="radio" name="exampleOx" value="0" checked="checked"/></td>
									</tr>
									<tr>
										<td class="text-bg-light"><input class="form-control" type="hidden" name="exampleIdx" value="2">보기 2</td>
										<td><input class="form-control form-control-sm" type="text" name="exampleTitle" required="required"/></td>
										<td><input class="form-check-input" type="radio" name="exampleOx" value="1"/></td>
									</tr>
									<tr>
										<td class="text-bg-light"><input class="form-control" type="hidden" name="exampleIdx" value="3">보기 3</td>
										<td><input class="form-control form-control-sm" type="text" name="exampleTitle" required="required"/></td>
										<td><input class="form-check-input " type="radio" name="exampleOx" value="2"/></td>
									</tr>
									<tr>
										<td class="text-bg-light"><input class="form-control" type="hidden" name="exampleIdx" value="4">보기 4</td>
										<td><input class="form-control form-control-sm" type="text" name="exampleTitle" required="required"/></td>
										<td><input class="form-check-input" type="radio" name="exampleOx" value="3"></td>
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
		<hr/>
		<!-- 시험 문제 목록 -->
		<c:if test="${empty questionList}">
			<div class="d-flex justify-content-center mt-5">생성된 문제가 없습니다.</div>
		</c:if>
		<div class="container" style="column-count:2; gap: 100px; column-rule: 1px solid #ced4da;">
			<c:if test="${not empty questionList}">
				<c:forEach var="q" items="${questionList}">
					<c:if test="${q.exampleIdx == 1 || q.exampleIdx == null}">
						<div class="col-12 mt-3 mb-3">
							<span class="fw-bold">${q.questionIdx}. ${q.questionTitle}</span>
							<!-- 문제 수정 modal -->
							<button class="btn btn-sm btn-primary float-end" type="button" data-bs-toggle="modal" data-bs-target="#${q.questionNo}">문제 관리</button>		
							<!-- Modal -->
							<div class="modal fade" id="${q.questionNo}" data-bs-backdrop="static"
								data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content rounded-0">
										<div class="modal-body container">
											<div class="clearfix shadow text-white bg-primary p-2">
												<span class="float-start">문제 관리</span>
												<span class="float-end"><button type="button" class="btn-close" data-bs-dismiss="modal"></button></span>
											</div>
											<form action="${pageContext.request.contextPath}/teacher/test/removeQuestion" method="post">
												<input type="hidden" name="questionNo" value="${q.questionNo}">
												<input type="hidden" name="testNo" value="${q.testNo}">
												<div class="d-grid gap-2">
													<button class="btn btn-sm btn-danger float-end mt-3" type="submit">문제 삭제</button>
												</div>
											</form>
											<form class="form form-horizontal p-4" action="${pageContext.request.contextPath}/teacher/test/modifyQuestion" method="post">
												<input type="hidden" name="testNo" value="${testOne.testNo}"/>
												<input type="hidden" name="questionNo" value="${q.questionNo}">											
												<!-- 문제 -->
												<table class="table table-bordered">
													<tr>
														<th class="text-bg-light">문제 번호</th>
														<td>
															<input type="hidden" name="testNo" value="${testOne.testNo}"/>
															<input class="form-control form-control-sm" type="number" name="questionIdx" value="${q.questionIdx}" required="required"/>
														</td>
													</tr>
													<tr>
														<th class="text-bg-light">문제 설명</th>
														<td>
															<textarea class="form-control form-control-sm" rows="5" cols="30" name="questionTitle" required="required">${q.questionTitle}</textarea>
														</td>
													</tr>
												</table>
												<!-- 보기(객관식) -->
												<table class="table table-bordered table-hover mt-3">
													<tr>
														<th class="text-bg-light text-center">번호</th>
														<th class="text-bg-light text-center">보기</th>
														<th class="text-bg-light text-center">정답여부</th>
													</tr>
													<c:forEach var="e" items="${questionList}">
														<c:if test="${q.questionNo eq e.questionNo}">
															<tr>
																<td class="text-bg-light text-center">
																	<input type="hidden" name="exampleNo" value="${e.exampleNo}">
																	<input type="hidden" name="exampleIdx" value="${e.exampleIdx}">보기 ${e.exampleIdx}
																</td>
																<td><input class="form-control form-control-sm" type="text" name="exampleTitle" value="${e.exampleTitle}" required="required"/></td>
																<td>
																	<c:if test="${e.exampleOx eq '정답'}">
																		<input class="form-check-input" type="radio" name="exampleOx" value="${e.exampleIdx -1}" checked="checked" required="required"/>
																	</c:if>
																	<c:if test="${e.exampleOx eq '오답'}">
																		<input class="form-check-input" type="radio" name="exampleOx" value="${e.exampleIdx -1}" required="required"/>
																	</c:if>
																</td>
															</tr>
														</c:if>
													</c:forEach>
												</table>
												<div class="d-grid gap-2">
													<button class="btn btn-lg btn-primary" type="submit">수정</button>
													<button class="btn btn-lg btn-secondary" type="button" data-bs-dismiss="modal">취소</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${q.exampleIdx != null}">
						<div class="px-3">
							<div>
								<c:if test="${q.exampleIdx == 1}">
									<span style="position: relative;">
										①
										<c:if test="${q.exampleOx eq '정답'}">
											<span style="position: absolute; top: -3px; left: -1px;">
												<img alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
											</span>
										</c:if>
									</span>
								</c:if>
								<c:if test="${q.exampleIdx == 2}">
									<span style="position: relative;">
										②
										<c:if test="${q.exampleOx eq '정답'}">
											<span style="position: absolute; top: -3px; left: -1px;">
												<img alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
											</span>
										</c:if>
									</span>
								</c:if>
								<c:if test="${q.exampleIdx == 3}">
									<span style="position: relative;">
										③
										<c:if test="${q.exampleOx eq '정답'}">
											<span style="position: absolute; top: -3px; left: -1px;">
												<img alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
											</span>
										</c:if>
									</span>
								</c:if>
								<c:if test="${q.exampleIdx == 4}">
									<span style="position: relative;">
										④
										<c:if test="${q.exampleOx eq '정답'}">
											<span style="position: absolute; top: -3px; left: -1px;">
												<img alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
											</span>
										</c:if>
									</span>
								</c:if>
								${q.exampleTitle}
							</div>
							<input type="hidden" name="testNo" value="${testOne.testNo}"/>
							<input type="hidden" name="exampleNo" value="${q.exampleNo}">
						</div>
					</c:if>
					<c:if test="${q.exampleIdx == null}">
						등록된 보기가 없습니다.
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		<script>
			$(function() {
				let msg = '${msg}';
				if(msg == 'MODIFY_SUCCESS') {
					alert('수정 완료');
				}
				if(msg == 'MODIFY_AUTH_ERROR') {
					alert('해당 권한이 없습니다.');
				}
				if(msg == 'MODIFY_ERROR') {
					alert('수정 실패');
				}
				
				if(msg == 'DELETE_SUCCESS') {
					alert('삭제 성공');
				}
				if(msg == 'DELETE_AUTH_ERROR') {
					alert('해당 권한이 없습니다.');
				}
				if(msg == 'DELETE_ERROR') {
					alert('삭제 실패');
				}
			});
		</script>
	</body>
</html>