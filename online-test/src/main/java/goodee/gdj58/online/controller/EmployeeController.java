package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.vo.Employee;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	// 직원 로그인 
	@GetMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session) {
		// 로그인 상태 체크
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp != null) {
			return "redirect:/employee/empList";
		}
		
		return "/employee/loginEmp";
	}
	
	@PostMapping("/employee/loginEmp")
	public String loginEmp(HttpSession session, Employee emp) {
		// 로그인 상태 체크
		Employee resultEmp = employeeService.login(emp);
		if(resultEmp == null) { // 로그인 실패 시
			System.out.println("로그인 실패");
			return "redirect:/employee/loginEmp";
		}
		// 로그인 성공 시
		System.out.println("로그인 성공");
		session.setAttribute("loginEmp", resultEmp);
		
		return "redirect:/employee/empList";
	}
	
	// 직원 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/employee/loginEmp";
	}
	
	
	/*
	 *  로그인 후에 사용가능한 기능
	 */

	
	// 직원 삭제
	@GetMapping("/employee/removeEmp")
	public String removeEmp(HttpSession session, @RequestParam("empNo") int empNo) {
		// 로그인 상태 체크
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		int row = employeeService.removeEmployee(empNo);
		
		if(row == 1) {
			System.out.println("직원 삭제 성공");			
		} else {
			System.out.println("직원 삭제 실패");
		}
		
		return "redirect:/employee/empList";
	}
	
	// 직원 등록
	@GetMapping("/employee/addEmp")
	public String addEmp(HttpSession session) {
		// 로그인 상태 체크
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		return "/employee/addEmp";
	}
	
	@PostMapping("/employee/addEmp")
	public String addEmp(HttpSession session, Employee employee) { // 커맨드객체, 만약 vo랑 name 같아야 됨(폼타입 vo), @RequestParam으로 받을 수 있지만 수가 많아지면 비효율적이다.
		// 로그인 상태 체크
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		int row = employeeService.addEmployee(employee);
		
		if(row == 1) {
			System.out.println("직원 등록 성공");			
		} else {
			System.out.println("직원 등록 실패");
		}
		
		return "redirect:/employee/empList";
	}
	
	// 직원 목록
	@GetMapping("/employee/empList")
	public String empList(HttpSession session, Model model 
			// int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// if( ... == null ...) -> defaultValue
			, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		// 로그인 상태 체크
		Employee loginEmp = (Employee)session.getAttribute("loginEmp");
		if(loginEmp == null) {
			return "redirect:/employee/loginEmp";
		}
		
		List<Employee> list = employeeService.getEmployeeList(currentPage, rowPerPage);
		
		// model ...request.setAttribute("list", list);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", currentPage);
		
		return "/employee/empList";
	}
}