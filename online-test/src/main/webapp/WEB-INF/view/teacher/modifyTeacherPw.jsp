<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>MODIFY TEACHER</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body style="height: 100vh; background-color: rgb(238,238,238,0.3);">
		<!-- teacherMenu include -->
		<c:import url="/WEB-INF/view/teacher/inc/teacherMenu.jsp" />
		<!-- main -->
		<div class="container h-100 d-flex justify-content-center align-items-center">
			<section class="section d-flex justify-content-center">
				<div class="col-md-12">
					<div class="card mt-3 p-3">
						<div class="card-body">
							<h1 class="mt-3">비밀번호 수정</h1>
							<span class="text-danger">${errorMsg}</span>
							<form class="form form-horizontal px-4 mt-5" action="${pageContext.request.contextPath}/teacher/modifyTeacherPw" method="post">
								<table class="table table-hover mt-3">
									<tr>
										<td>비밀번호</td>
										<td><input class="form-control" type="password" name="oldPw"/></td>
									</tr>
									<tr>
										<td>새 비밀번호</td>
										<td><input class="form-control" type="password" name="newPw"/></td>
									</tr>
								</table>
								<div class="d-grid gap-2">
									<button class="btn btn-lg btn-primary" type="submit">수정</button>
								</div>
							</form>
						</div>			
					</div>
				</div>
			</section>
		</div>
	</body>
</html>