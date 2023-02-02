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
import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.vo.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	IdService idService; // 트랜잭션 처리 시 service단으로
	
	// 직원 비밀번호 수정
	@GetMapping("/employee/modifyEmployeePw")
	public String modifyEmployeePw() {
		log.debug("\u001B[31m" + "modifyEmployeePw Form");
		
		return "/employee/modifyEmployee";
	}
	
	@PostMapping("/employee/modifyEmployeePw")
	public String modifyEmployeePw(HttpSession session
									, Model model
									, @RequestParam(value = "oldPw") String oldPw 
									, @RequestParam(value = "newPw") String newPw) {
		log.debug("\u001B[31m" + "modifyEmployeePw Action");
		
		Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
		int row = employeeService.modifyEmployeePw(loginEmployee.getEmployeeNo(), oldPw, newPw);
		
		if(row != 1) {
			log.debug("\u001B[31m" + "직원 비밀번호 수정 실패");
			model.addAttribute("errorMsg", "비밀번호를 확인해주세요.");
			
			return "/employee/modifyEmployee";
		}
		log.debug("\u001B[31m" + "직원 비밀번호 수정 성공");			
				
		return "redirect:/employee/logout";
	}
	
	// 직원 로그인 
	@GetMapping("/loginEmployee")
	public String loginEmployee() {
		log.debug("\u001B[31m" + "loginEmployee Form");
		
		return "/employee/loginEmployee";
	}
	
	@PostMapping("/loginEmployee")
	public String loginEmployee(HttpSession session, Employee employee) {
		log.debug("\u001B[31m" + "loginEmployee Action");
	
		Employee resultEmployee = employeeService.login(employee);
		
		if(resultEmployee == null) {
			log.debug("\u001B[31m" + "로그인 실패");
			return "redirect:/employee/loginEmployee";
		}
		
		session.setAttribute("loginEmployee", resultEmployee);
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		log.debug("\u001B[31m" + "logoutEmployee Action");
		
		session.invalidate();
		
		return "redirect:/employee/loginEmployee";
	}
	
	// 직원 삭제
	@GetMapping("/employee/removeEmployee")
	public String removeEmployee(@RequestParam("employeeNo") int employeeNo) {
		log.debug("\u001B[31m" + "removeEmployee Action");
		
		int row = employeeService.removeEmployee(employeeNo);
		
		if(row == 1) {
			log.debug("\u001B[31m" + "직원 삭제 성공");			
		} else {
			log.debug("\u001B[31m" + "직원 삭제 실패");
		}
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 등록
	@GetMapping("/employee/addEmployee")
	public String addEmployee() {
		log.debug("\u001B[31m" + "addEmployee Form");
		
		return "/employee/addEmployee";
	}
	
	@PostMapping("/employee/addEmployee")
	public String addEmployee(HttpSession session, Model model, Employee employee) { // 커맨드객체, 만약 vo랑 name 같아야 됨(폼타입 vo), @RequestParam으로 받을 수 있지만 수가 많아지면 비효율적이다.
		log.debug("\u001B[31m" + "addEmployee Action");
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(employee.getEmployeeId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된 ID입니다.");
			log.debug("\u001B[31m" + "중복된 직원 ID");
			
			return "/employee/addEmployee";
		}
		
		int row = employeeService.addEmployee(employee);
		
		if(row != 1) {
			model.addAttribute("errorMsg", "system error : 직원 등록 실패");
			log.debug("\u001B[31m" + "직원 등록 실패");
		}
		log.debug("\u001B[31m" + "직원 등록 성공");	
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 목록
	@GetMapping("/employee/employeeList")
	public String employeeList(HttpSession session, Model model 
			// int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// if( ... == null ...) -> defaultValue
			, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug("\u001B[31m" + "employeeList Form");
		
		List<Employee> employeeList = employeeService.getEmployeeList(currentPage, rowPerPage);
		
		// model ...request.setAttribute("list", list);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("currentPage", currentPage);
		
		return "/employee/employeeList";
	}
}