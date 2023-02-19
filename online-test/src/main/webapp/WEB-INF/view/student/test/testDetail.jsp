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
	<body style="background-color: #eee;">
		<!-- studentMenu include -->
		<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp" />
		
		<!-- 시험 문제 목록 -->
		<c:if test="${not empty questionList}">
			<div class="container bg-white p-5" style="column-count:2; gap: 100px; column-rule: 1px solid #ced4da;">
				<h1 class="mt-5">시험지</h1>
				<form action="${pageContext.request.contextPath}/student/test/addPaper" method="post">
					<input type="hidden" name="studentNo" value="${loginStudent.studentNo}">
						<c:forEach var="q" items="${questionList}">
							<c:if test="${q.exampleIdx == 1 || q.exampleIdx == null}">
								<input type="hidden" name="testNo" value="${q.testNo}">
								<input type="hidden" name="questionNo" value="${q.questionNo}">
								<input id="${q.questionNo}" type="hidden" name="answer" value="0">
								<div class="fw-bold mt-5 mb-3">${q.questionIdx}. ${q.questionTitle} ${q.questionNo }</div>
							</c:if>
							<div>
								<div>
									<c:if test="${q.exampleIdx != null}">
										<c:if test="${q.exampleIdx == 1}">
											<span style="position: relative;">
												<label for="${q.questionNo}${q.exampleIdx}" class="radio-label">
													① ${q.exampleTitle}
													<img class="${q.questionNo}" style="display:none; position: absolute; top: -3px; left: -1px;" alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
												</label>
												<input style="position: absolute; left: -9999px;" type="radio" name="${q.questionNo}" value="${q.exampleIdx}" id="${q.questionNo}${q.exampleIdx}">
											</span>
										</c:if>
										<c:if test="${q.exampleIdx == 2}">
											<span style="position: relative;">
												<label for="${q.questionNo}${q.exampleIdx}" class="radio-label">
													② ${q.exampleTitle}
													<img class="${q.questionNo}" style="display:none; position: absolute; top: -3px; left: -1px;" alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
												</label>
												<input style="position: absolute; left: -9999px;" type="radio" name="${q.questionNo}" value="${q.exampleIdx}" id="${q.questionNo}${q.exampleIdx}">
											</span>
										</c:if>
										<c:if test="${q.exampleIdx == 3}">
											<span style="position: relative;">
											<label for="${q.questionNo}${q.exampleIdx}" class="radio-label">
												③ ${q.exampleTitle}
												<img class="${q.questionNo}" style="display:none; position: absolute; top: -3px; left: -1px;" alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
											</label>
											<input style="position: absolute; left: -9999px;" type="radio" name="${q.questionNo}" value="${q.exampleIdx}" id="${q.questionNo}${q.exampleIdx}">
											</span>
										</c:if>
										<c:if test="${q.exampleIdx == 4}">
											<span style="position: relative;">
												<label for="${q.questionNo}${q.exampleIdx}" class="radio-label">
													④ ${q.exampleTitle}
													<img class="${q.questionNo}" style="display:none; position: absolute; top: -3px; left: -1px;" alt="체크" width="20px;" src="${pageContext.request.contextPath}/images/checkmark.jpg">
												</label>
												<input style="position: absolute; left: -9999px;" type="radio" name="${q.questionNo}" value="${q.exampleIdx}" id="${q.questionNo}${q.exampleIdx}">
											</span>
										</c:if>
										<c:if test="${q.exampleIdx == null}">
											등록된 보기가 없습니다.
										</c:if>
									</c:if>
								</div>
							</div>
						</c:forEach>
					<button class="btn btn-primary mt-5" type="submit">제출하기</button>
				</form>
			</div>
		</c:if>
		<script>
			<c:forEach var="q" items="${questionList}">
				$(function() {
					if (${q.exampleIdx == 1 || q.exampleIdx == null}) {
						$('input[name=${q.questionNo}]').click(function() {
							let anwser = $('input[name=${q.questionNo}]:checked').val();	
							$('#${q.questionNo}').val(anwser);
						});								
					}
				});
			</c:forEach>
			
			if(${empty questionList}) {
				alert('생성된 문제가 없습니다.');
				history.back();
			}
			
			$('.radio-label').click(function() {
			    var radioId = $(this).attr('for');
			    var radioName = $('#' + radioId).attr('name');

			    $('.' + radioName).hide();
				
			    $('#' + radioId).prop('checked', true);
			    $(this).find('img').show();
			});
		</script>
	</body>
</html>