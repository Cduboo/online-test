<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<title>EMPLOYEE MAIN</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" />
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Icon Font Stylesheet -->
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
	    <!-- chart.js -->
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
	    <script src="https://kit.fontawesome.com/def66b134a.js" crossorigin="anonymous"></script>
	    <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
	</head>
	<body>
		<!-- empMenu include -->
		<div>
			<c:import url="/WEB-INF/view/employee/inc/employeeMenu.jsp" />
		</div>
		<!-- total container Start -->
		<div class="container pt-4 px-4">
			<div class="row g-4">
				<div class="col-sm-6 col-xl-3">
					<div
						class="bg-light rounded d-flex align-items-center justify-content-between p-4">
						<i class="fas fa-users fa-3x text-primary"></i>
						<div class="ms-3">
							<p class="mb-2 fw-bold">총 직원 수</p>
							<h6 class="mb-0 fst-italic">${totalCount.employeeCount}</h6>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-xl-3">
					<div
						class="bg-light rounded d-flex align-items-center justify-content-between p-4">
						<i class="fas fa-chalkboard-teacher fa-3x text-primary"></i>
						<div class="ms-3">
							<p class="mb-2 fw-bold">총 강사 수</p>
							<h6 class="mb-0 fst-italic">${totalCount.teacherCount}</h6>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-xl-3">
					<div
						class="bg-light rounded d-flex align-items-center justify-content-between p-4">
						<i class="fas fa-user-graduate fa-3x text-primary"></i>
						<div class="ms-3">
							<p class="mb-2 fw-bold" >총 학생 수</p>
							<h6 class="mb-0 fst-italic">${totalCount.studentCount}</h6>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-xl-3">
					<div
						class="bg-light rounded d-flex align-items-center justify-content-between p-4">
						<i class="fa fa-chart-pie fa-3x text-primary"></i>
						<div class="ms-3">
							<p class="mb-2 fw-bold">총 시험 수</p>
							<h6 class="mb-0 fst-italic">${totalCount.testCount}</h6>
						</div>
					</div>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-sm-12 col-md-5">
					<div class="bg-light rounded h-100 p-4 d-flex align-items-center">
						<h6 class="mb-4 fw-bold">가입자 현황</h6>
						<div>
							<canvas id="pie-chart" height="300"></canvas>
						</div>
					</div>
				</div>
				<div class="col-sm-12 col-md-7">
					<div class="bg-light rounded h-100 p-4">
						<h6 class="mb-4 fw-bold">년도별 시험 등록</h6>
  						<div class="d-flex justify-content-center align-items-center">
				        	<button id="prev" class="btn" type="button" style="font-size: 21px">&lt;</button>
					        <span id="year" class="fw-bold" style="font-size: 21px">${year}</span>
					        <button id="next" class="btn" type="button" style="font-size: 21px">&gt;</button>
					    </div>
						<canvas id="testChart" style="width:100%"></canvas>
					</div>
				</div>
			</div>
			<div class="row mt-3">
				<div class="col-sm-12 col-md-4">
					<div class="bg-light rounded h-100 p-4 table-responsive">
						<h6 class="mb-5 fw-bold">최근 가입한 직원 목록</h6>
						<table class="table bg-white talbe-border table-hover text-center">
							<thead>
								<tr class="table-secondary">
									<th>아이디</th>
									<th>이름</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="e" items="${recentList[0]}">
									<tr>
										<td>${e.employeeId}</td>
										<td>${e.employeeName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-12 col-md-4">
					<div class="bg-light rounded h-100 p-4 table-responsive">
						<h6 class="mb-5 fw-bold">최근 가입한 강사 목록</h6>
						<table class="table bg-white talbe-border table-hover text-center">
							<thead>
								<tr class="table-secondary">
									<th>아이디</th>
									<th>이름</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="t" items="${recentList[1]}">
									<tr>
										<td>${t.teacherId}</td>
										<td>${t.teacherName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-12 col-md-4">
					<div class="bg-light rounded h-100 p-4 table-responsive">
						<h6 class="mb-5 fw-bold">최근 가입한 학생 목록</h6>
						<table class="table bg-white talbe-border table-hover text-center">
							<thead>
								<tr class="table-secondary">
									<th>아이디</th>
									<th>이름</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="s" items="${recentList[2]}">
									<tr>
										<td>${s.studentId}</td>
										<td>${s.studentName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<c:forEach var="m" items="${recentList[3]}">
			${m.month}
		</c:forEach>
		<!-- total container End -->
		<script>
	 		/* 직원, 강사, 학생 비율 원 그래프 */
			function drawPieChart() {
				var xValues = ["직원", "강사", "학생"];
				var yValues = [${totalCount.employeeCount}, ${totalCount.teacherCount}, ${totalCount.studentCount}];
				var barColors = [
				  "rgba(50, 93, 136, 0.7)",
				  "rgba(14, 127, 239, 0.5)",
				  "#8dc4fc",
				];
	
				new Chart("pie-chart", {
				  type: "pie",
				  data: {
				    labels: xValues,
				    datasets: [{
				      backgroundColor: barColors,
				      data: yValues
				    }]
				  },
				  options: {
				    title: {
				      display: true,
				      text: "가입자 현황"
				    },
				    legend: {
				    	position: 'bottom'
				    },
				    maintainAspectRatio: false,
				  }
				});
		 	}
	 		
			/* 년도별 각 월의 시험 개수 그래프 */
			function drawlineChart() {
				$.ajax({
					url : '${pageContext.request.contextPath}/employee/testCountByMonth',
					type : 'get',
					data : {year : $('#year').text()},
					dataType: "json",
					success : function(data) {
						
						var xValues = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
						var yValues = [0,0,0,0,0,0,0,0,0,0,0,0];
						
						data.forEach(item => {
							for (var i = 1; i <= 12; i++) {
								if(i == item.month) {
									yValues[i-1] = item.count;
								}
							}				
						});
						
						new Chart("testChart", {
						  type: "line",
						  data: {
						    labels: xValues,
						    datasets: [{
						      fill: true,
						      lineTension: 0,
						      backgroundColor: "rgba(50, 93, 136,0.5)",
						      borderColor: "rgba(50, 93, 136,0.5)",
						      data: yValues
						    }]
						  },
						  options: {
						    legend: {display: false},
						    scales: {
						      yAxes: [{ticks: {min: 0,stepSize: 1 }}],
						    }
						  }
						});	
					}		
				});
			}
			
			/* 년도 조작 */
			function changeYear(delta) {
			  var year = Number($('#year').text()) + Number(delta);
			  $('#year').text(year);
			  drawlineChart();
			}
		
			$(function() {
				drawlineChart();
				drawPieChart();
				
				$('#prev').click(function() {
					changeYear(-1);
				});  
			 	$('#next').click(function() {
			    	changeYear(1);
			  	});
			});
		</script>
	</body>
</html>