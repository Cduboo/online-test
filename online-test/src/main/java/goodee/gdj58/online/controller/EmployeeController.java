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
	@GetMapping("/employee/loginEmployee")
	public String loginEmployee(HttpSession session) {
		// 로그인 상태 체크
		Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
		if(loginEmployee != null) {
			return "redirect:/employee/employeeList";
		}
		
		return "/employee/loginEmployee";
	}
	
	@PostMapping("/employee/loginEmployee")
	public String loginEmployee(HttpSession session, Employee employee) {
		// 로그인 상태 체크
		Employee resultEmployee = employeeService.login(employee);
		if(resultEmployee == null) { // 로그인 실패 시
			System.out.println("로그인 실패");
			return "redirect:/employee/loginEmployee";
		}
		// 로그인 성공 시
		System.out.println("로그인 성공");
		session.setAttribute("loginEmployee", resultEmployee);
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/employee/loginEmployee";
	}
	
	
	/*
	 *  로그인 후에 사용가능한 기능
	 */

	
	// 직원 삭제
	@GetMapping("/employee/removeEmployee")
	public String removeEmployee(HttpSession session, @RequestParam("employeeNo") int employeeNo) {
		// 로그인 상태 체크
		Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
		if(loginEmployee == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		int row = employeeService.removeEmployee(employeeNo);
		
		if(row == 1) {
			System.out.println("직원 삭제 성공");			
		} else {
			System.out.println("직원 삭제 실패");
		}
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 등록
	@GetMapping("/employee/addEmployee")
	public String addEmployee(HttpSession session) {
		// 로그인 상태 체크
		Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
		if(loginEmployee == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		return "/employee/addEmployee";
	}
	
	@PostMapping("/employee/addEmployee")
	public String addEmployee(HttpSession session, Employee employee) { // 커맨드객체, 만약 vo랑 name 같아야 됨(폼타입 vo), @RequestParam으로 받을 수 있지만 수가 많아지면 비효율적이다.
		// 로그인 상태 체크
		Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
		if(loginEmployee == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		int row = employeeService.addEmployee(employee);
		
		if(row == 1) {
			System.out.println("직원 등록 성공");			
		} else {
			System.out.println("직원 등록 실패");
		}
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 목록
	@GetMapping("/employee/employeeList")
	public String employeeList(HttpSession session, Model model 
			// int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// if( ... == null ...) -> defaultValue
			, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		// 로그인 상태 체크
		Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
		if(loginEmployee == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		List<Employee> employeeList = employeeService.getEmployeeList(currentPage, rowPerPage);
		
		// model ...request.setAttribute("list", list);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("currentPage", currentPage);
		
		return "/employee/employeeList";
	}
}