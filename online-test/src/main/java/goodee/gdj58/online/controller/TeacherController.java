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
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;

@Controller
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	@Autowired
	IdService idService;
	
	// 강사 등록
	@GetMapping("/teacher/addTeacher")
	public String addTeacher(HttpSession session) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		return "/teacher/addTeacher";
	}
	
	@PostMapping("/teacher/addTeacher")
	public String addStudent(HttpSession session, Model model, Teacher teacher) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(teacher.getTeacherId());
		if(idCheck != null) {
			System.out.println("중복된 강사 ID");
			model.addAttribute("errorMsg", "중복된 ID");
			
			return "/teacher/addTeacher";
		}
		
		int row = teacherService.addStudent(teacher);
		
		if(row != 1) {
			System.out.println("강사 등록 실패");
			model.addAttribute("errorMsg", "system error : 강사 등록 실패");
		}
		System.out.println("강사 등록 성공");
		
		return "redirect:/teacher/teacherList";
	}
	
	// 강사 삭제
	@GetMapping("/teacher/removeTeacher")
	public String removeTeacher(HttpSession session
									, @RequestParam(value = "teacherNo") int teacherNo) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		int row = teacherService.removeTeacher(teacherNo);
		
		if(row == 1) {
			System.out.println("강사 삭제 성공");
		} else {
			System.out.println("강사 삭제 실패");
		}
		
		return "redirect:/teacher/teacherList";
	}
	
	// 강사 목록
	@GetMapping("/teacher/teacherList")
	public String getTeacherList(HttpSession session
									, Model model
									, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
									, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		// 로그인 상태 체크
		Employee employeeId = (Employee)session.getAttribute("loginEmployee");
		if(employeeId == null) {
			return "redirect:/employee/loginEmployee";
		}
		
		List<Teacher> teacherList = teacherService.getTeacherList(currentPage, rowPerPage);
		
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("currentPage", currentPage);
		
		return "/teacher/teacherList";
	}
}
