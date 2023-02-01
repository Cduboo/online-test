package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;

@Controller
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	IdService idService;
	
	// 학생 등록
	@GetMapping("/student/addStudent")
	public String addStudent(HttpSession session) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		return "/student/addStudent";
	}
	
	@PostMapping("/student/addStudent")
	public String addStudent(HttpSession session, Model model, Student student) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			System.out.println("중복된 학생 ID");
			model.addAttribute("errorMsg", "중복된 ID");
			
			return "/student/addStudent";
		}
		
		int row = studentService.addStudent(student);
		
		if(row != 1) {
			System.out.println("학생 등록 실패");
			model.addAttribute("errorMsg", "system error : 학생 등록 실패");
		}
		System.out.println("학생 등록 성공");
		
		return "redirect:/student/studentList";
	}
						
	// 학생 삭제
	@GetMapping("/student/removeStudent")
	public String removeStudent(HttpSession session
									, @RequestParam(value = "studentNo") int studentNo) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		int row = studentService.removeStudent(studentNo);
		
		if(row == 1) {
			System.out.println("학생 삭제 성공");
		} else {
			System.out.println("학생 삭제 실패");
		}
		
		return "redirect:/student/studentList";
	}
	
	// 학생 목록
	@GetMapping("/student/studentList")
	public String getStudentList(HttpSession session
									, Model model
									, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
									, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		List<Student> studentList = studentService.getStudentList(currentPage, rowPerPage);
		
		model.addAttribute("studentList", studentList);
		model.addAttribute("currentPage", currentPage);
		
		return "/student/studentList";
	}
}
