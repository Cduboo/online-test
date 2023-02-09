<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>TESTRESULT</title>
	</head>
	<body>
		<!-- studentMenu include -->
		<div>
			<c:import url="/WEB-INF/view/student/inc/studentMenu.jsp" />
		</div>
		
		<c:forEach var="tr" items="${testResult}">
			<c:if test="${tr.questionIdx == 1}">
				${tr.testTitle}
			</c:if> 
			${tr.questionIdx}
			${tr.answer}
			${tr.exampleIdx}
			${tr.exampleOx}
		</c:forEach>
	</body>
</html>