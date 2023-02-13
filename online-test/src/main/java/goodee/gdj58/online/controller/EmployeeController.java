package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	String logRed = "\u001B[31m";
	
	// 직원 메인 페이지
	@GetMapping("/employee/employeeMain")
	public String getEmployeeMain() {
		log.debug(logRed + "employee Form");
		
		return "/employee/employeeMain";
	}
	
	// 직원 비밀번호 수정
	@GetMapping("/employee/modifyEmployeePw")
	public String modifyEmployeePw() {
		log.debug(logRed + "modifyEmployeePw Form");
		
		return "/employee/modifyEmployee";
	}
	
	@PostMapping("/employee/modifyEmployeePw")
	public String modifyEmployeePw(HttpSession session
									, Model model
									, @RequestParam(value = "oldPw") String oldPw 
									, @RequestParam(value = "newPw") String newPw) {
		log.debug(logRed + "modifyEmployeePw Action");
		
		Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
		int row = employeeService.modifyEmployeePw(loginEmployee.getEmployeeNo(), oldPw, newPw);
		
		if(row != 1) {
			log.debug(logRed + "직원 비밀번호 수정 실패");
			model.addAttribute("errorMsg", "비밀번호를 확인해주세요.");
			
			return "/employee/modifyEmployee";
		}
		log.debug(logRed + "직원 비밀번호 수정 성공");			
				
		return "redirect:/employee/logout";
	}
	
	// 직원 로그인 
	@PostMapping("/loginEmployee")
	public String loginEmployee(HttpSession session
									, RedirectAttributes re
									, @ModelAttribute Employee employee /*Employee employee*/) {
		log.debug(logRed + "loginEmployee Action");
	
		Employee resultEmployee = employeeService.login(employee);
		
		if(resultEmployee == null) {
			log.debug(logRed + "로그인 실패");
			re.addFlashAttribute("msg", "LOGIN_ERROR");
			return "redirect:/loginEmployee";
		}
		
		session.setAttribute("loginEmployee", resultEmployee);
		
		return "redirect:/employee/employeeMain";
	}
	
	// 직원 로그아웃
	@GetMapping("/employee/logout")
	public String logout(HttpSession session) {
		log.debug(logRed + "logoutEmployee Action");
		
		session.invalidate();
		log.debug(logRed + "sessiong invalidate");
		
		return "redirect:/home";
	}
	
	// 직원 삭제
	@GetMapping("/employee/removeEmployee")
	public String removeEmployee(@RequestParam("employeeNo") int employeeNo) {
		log.debug(logRed + "removeEmployee Action");
		
		int row = employeeService.removeEmployee(employeeNo);
		
		if(row == 1) {
			log.debug(logRed + "직원 삭제 성공");			
		} else {
			log.debug(logRed + "직원 삭제 실패");
		}
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 등록
	@GetMapping("/employee/addEmployee")
	public String addEmployee() {
		log.debug(logRed + "addEmployee Form");
		
		return "/employee/addEmployee";
	}
	
	@PostMapping("/employee/addEmployee")
	public String addEmployee(HttpSession session, Model model, Employee employee) { // 커맨드객체, 만약 vo랑 name 같아야 됨(폼타입 vo), @RequestParam으로 받을 수 있지만 수가 많아지면 비효율적이다.
		log.debug(logRed + "addEmployee Action");
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(employee.getEmployeeId());
		if(idCheck != null) {
			model.addAttribute("errorMsg", "중복된 ID입니다.");
			log.debug(logRed + "중복된 직원 ID");
			
			return "/employee/addEmployee";
		}
		
		int row = employeeService.addEmployee(employee);
		
		if(row != 1) {
			model.addAttribute("errorMsg", "system error : 직원 등록 실패");
			log.debug(logRed + "직원 등록 실패");
		}
		log.debug(logRed + "직원 등록 성공");	
		
		return "redirect:/employee/employeeList";
	}
	
	// 직원 목록
	@GetMapping("/employee/employeeList")
	public String employeeList(HttpSession session, Model model 
			// int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			// if( ... == null ...) -> defaultValue
			, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
			, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		log.debug(logRed + "employeeList Form");
		log.debug(logRed + "currentPage : " + currentPage);
		log.debug(logRed + "rowPerPage : " + rowPerPage);
		log.debug(logRed + "searchWord : " + searchWord);
		
		List<Employee> employeeList = employeeService.getEmployeeList(currentPage, rowPerPage, searchWord);
		int employeeCount = employeeService.getEmployeeCount(searchWord);
		int lastPage = employeeCount / rowPerPage;
		if(employeeCount % rowPerPage != 0) {
			lastPage++;
		}
		//for문 1:1~10, 2:1~10, 3:1~10... 10: 1~10, 11: 11~20, 12:11~20, ...
		int startPage = currentPage - (currentPage-1) % rowPerPage; 
		int endPage = startPage + rowPerPage - 1;
		
		// model ...request.setAttribute("list", list);
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);	
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("rowPerPage", rowPerPage);	
		
		return "/employee/employeeList";
	}
}