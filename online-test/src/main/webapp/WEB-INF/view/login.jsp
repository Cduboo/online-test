<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>LOGIN</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body style="height: 100vh; background-color: rgb(238,238,238,0.3);">
		<!-- menu -->
		<c:import url="/WEB-INF/view/employee/inc/employeeMenu.jsp" />
		<!-- main -->
		<div class="container h-100 d-flex justify-content-center align-items-center">
			<section class="section d-flex justify-content-center">
				<div class="col-md-8">
					<div class="card p-3">
						<div class="card-body">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation"><a class="nav-link active" id="employeeLogin-tab" data-bs-toggle="tab" href="#employeeLogin" role="tab" aria-controls="employeeLogin" aria-selected="true">직원 로그인</a></li>
								<li class="nav-item" role="presentation"><a class="nav-link" id="teacherLogin-tab" data-bs-toggle="tab" href="#teacherLogin" role="tab" aria-controls="teacherLogin" aria-selected="false">강사 로그인</a></li>
								<li class="nav-item" role="presentation"><a class="nav-link" id="studentLogin-tab" data-bs-toggle="tab" href="#studentLogin" role="tab" aria-controls="studentLogin" aria-selected="false">학생 로그인</a></li>
							</ul>
							<div class="tab-content" id="myTabContent">
								<div class="tab-pane fade show active" id="employeeLogin" role="tabpanel" aria-labelledby="employeeLogin-tab">
									<!-- 직원 로그인 전 -->
									<c:if test="${empty loginEmployee}">
										<form id="employeeForm" class="form form-horizontal px-4 mt-5" action="${pageContext.request.contextPath}/loginEmployee" method="post">
											<div class="form-body">
												<div class="row">
													<div class="col-12">
														<div class="form-group has-icon-left">
															<label for="id">아이디</label>
															<div class="position-relative mt-1">
																<input type="text" name="employeeId" id="employeeId" class="form-control" placeholder="아이디">
																<div class="form-control-icon">
																	<i class="bi bi-person"></i>
																</div>
															</div>
														</div>
													</div>
													<div class="col-12 mt-3">
														<div class="form-group has-icon-left">
															<label for="pw">비밀번호</label>
															<div class="position-relative mt-1">
																<input type="password" name="employeePw" id="employeePw" class="form-control" placeholder="비밀번호">
																<div class="form-control-icon">
																	<i class="bi bi-lock"></i>
																</div>
															</div>
														</div>
													</div>
													<div class="d-grid gap-2 mt-4">
														<button type="button" id="employeeBtn" class="btn btn-lg btn-primary">로그인</button>
													</div>
												</div>
											</div>
										</form>
									</c:if>
								</div>	
								<div class="tab-pane fade" id="teacherLogin" role="tabpanel" aria-labelledby="teacherLogin-tab">
									<!-- 강사 로그인 전 -->
									<c:if test="${empty loginTeacher}">
										<form id="teacherForm" class="form form-horizontal px-4 mt-5" action="${pageContext.request.contextPath}/loginTeacher" method="post">
											<div class="form-body">
												<div class="row">
													<div class="col-12">
														<div class="form-group has-icon-left">
															<label for="id">아이디</label>
															<div class="position-relative mt-1">
																<input type="text" name="teacherId" id="teacherId" class="form-control" placeholder="아이디">
																<div class="form-control-icon">
																	<i class="bi bi-person"></i>
																</div>
															</div>
														</div>
													</div>
													<div class="col-12 mt-3">
														<div class="form-group has-icon-left">
															<label for="pw">비밀번호</label>
															<div class="position-relative mt-1">
																<input type="password" name="teacherPw" id="teacherPw" class="form-control" placeholder="비밀번호">
																<div class="form-control-icon">
																	<i class="bi bi-lock"></i>
																</div>
															</div>
														</div>
													</div>
													<div class="d-grid gap-2 mt-4">
														<button type="button" id="teacherBtn" class="btn btn-lg btn-primary">로그인</button>
													</div>
												</div>
											</div>
										</form>
									</c:if>
								</div>
								<div class="tab-pane fade" id="studentLogin" role="tabpanel" aria-labelledby="studentLogin-tab">
									<!-- 학생 로그인 전 -->
									<c:if test="${empty loginStudent}">
										<form id="studentForm" class="form form-horizontal px-4 mt-5" action="${pageContext.request.contextPath}/loginStudent" method="post">
											<div class="form-body">
												<div class="row">
													<div class="col-12">
														<div class="form-group has-icon-left">
															<label for="id">아이디</label>
															<div class="position-relative mt-1">
																<input type="text" name="studentId" id="studentId" class="form-control" placeholder="아이디">
																<div class="form-control-icon">
																	<i class="bi bi-person"></i>
																</div>
															</div>
														</div>
													</div>
													<div class="col-12 mt-3">
														<div class="form-group has-icon-left">
															<label for="pw">비밀번호</label>
															<div class="position-relative mt-1">
																<input type="password" name="studentPw" id="studentPw" class="form-control" placeholder="비밀번호">
																<div class="form-control-icon">
																	<i class="bi bi-lock"></i>
																</div>
															</div>
														</div>
													</div>
													<div class="d-grid gap-2 mt-4">
														<button type="button" id="studentBtn" class="btn btn-lg btn-primary">로그인</button>
													</div>
												</div>
											</div>
										</form>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
		<script>
			$(function() {
				/* 아이디 비밀번호 유효성 검사 */
				$('#employeeBtn').click(function() {
					if($('#employeeId').val() < 1) {
						alert('아이디를 입력해주세요.')
						return
					}if($('#employeePw').val() < 1) {
						alert('패스워드를 입력해주세요.')
						return
					}	
					
					$('#employeeForm').submit();
				})
				$('#teacherBtn').click(function() {
					if($('#teacherId').val() < 1) {
						alert('아이디를 입력해주세요.')
						return
					}if($('#teacherPw').val() < 1) {
						alert('패스워드를 입력해주세요.')
						return
					}	
					
					$('#teacherForm').submit();
				})
				$('#studentBtn').click(function() {
					if($('#studentId').val() < 1) {
						alert('아이디를 입력해주세요.')
						return
					}if($('#studentPw').val() < 1) {
						alert('패스워드를 입력해주세요.')
						return
					}	
					
					$('#studentForm').submit();
				})
							
				if('${msg}' == 'LOGIN_ERROR') {
					alert('아이디 또는 비밀번호를 확인해주세요.')
				}
			});
		</script>
	</body>
</html>