package goodee.gdj58.online.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.IdService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	IdService idService;
	String logRed = "\u001B[31m";
	
	// 학생 메인 페이지
	@GetMapping("/student/studentMain")
	public String getTeacherMain() {
		log.debug(logRed + "studentMain Form");
		
		return "/student/studentMain";
	}
	
	// 학생 로그인
	@PostMapping("/loginStudent")
	public String loginStudent(HttpSession session
								, RedirectAttributes re
								, Student student) {
		log.debug(logRed + "loginStudent Action");
		
		Student resultStudent = studentService.login(student);
		
		if(resultStudent == null) {
			log.debug(logRed + "로그인 실패");
			re.addFlashAttribute("msg", "LOGIN_ERROR");
			return "redirect:/home";
		}
		
		log.debug(logRed + "로그인 성공");
		session.setAttribute("loginStudent", resultStudent);
		
		return "redirect:/student/test/testList";
	}
	
	// 학생 로그아웃
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		log.debug(logRed + "logoutStudent Action");
		
		session.invalidate();
		log.debug(logRed + "sessiong invalidate");
		
		return "redirect:/home";
	}
	
	// 학생 비밀번호 수정
	@GetMapping("/student/modifyStudentPw")
	public String modifyStudentPw() {
		log.debug(logRed + "modifyStudentPw Form");
		
		return "/student/modifyStudentPw";
	}
	
	@PostMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session
									,Model model
									, @RequestParam(value = "oldPw") String oldPw
									, @RequestParam(value = "newPw") String newPw) {
		log.debug(logRed + "modifyStudentPw Action");
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int row = studentService.modifyStudentPw(loginStudent.getStudentNo(), newPw, oldPw);
		
		if(row != 1) {
			log.debug(logRed + "학생 비밀번호 수정 실패");
			model.addAttribute("errorMsg", "비밀번호를 확인해주세요.");
			
			return "/student/modifyStudentPw";
		}
		log.debug(logRed + "학생 비밀번호 수정 성공");
		
		return "redirect:/student/logout";
	}
	
	// 학생 등록
	@GetMapping("/employee/student/addStudent")
	public String addStudent() {
		log.debug(logRed + "addStudent Form");
		
		return "/employee/student/addStudent";
	}
	
	@PostMapping("/employee/student/addStudent")
	public String addStudent(Model model, Student student) {
		log.debug(logRed + "addStudent Action");
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			log.debug(logRed + "중복된 학생 ID");
			model.addAttribute("errorMsg", "중복된 ID");
			
			return "/employee/student/addStudent";
		}
		
		int row = studentService.addStudent(student);
		
		if(row != 1) {
			log.debug(logRed + "학생 등록 실패");
			model.addAttribute("errorMsg", "system error : 학생 등록 실패");
		}
		log.debug(logRed + "학생 등록 성공");
		
		return "redirect:/employee/student/studentList";
	}
						
	// 학생 삭제
	@PostMapping("/employee/student/removeStudent")
	public String removeStudent(@RequestParam(value = "studentNo", defaultValue = "0") int studentNo
									, RedirectAttributes re) {
		log.debug(logRed + "removeStudent Action");
		
		int row = studentService.removeStudent(studentNo);
		
		if(row == 1) {
			log.debug(logRed + "학생 삭제 성공");
		} else if (row == -1) {
			re.addFlashAttribute("msg", "REMOVE_ERROR");
			log.debug(logRed + "학생 삭제 불가 : 제출한 답안지 존재");
		} 
		else {
			log.debug(logRed + "학생 삭제 실패");
		}
		
		return "redirect:/employee/student/studentList";
	}
	
	// 학생 목록
	@GetMapping("/employee/student/studentList")
	public String getStudentList(Model model
									, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
									, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
									, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		log.debug(logRed + "studentList Form");
		log.debug(logRed + "currentPage : " + currentPage);
		log.debug(logRed + "rowPerPage : " + rowPerPage);
		log.debug(logRed + "searchWord : " + searchWord);
		
		List<Student> studentList = studentService.getStudentList(currentPage, rowPerPage, searchWord);
		int studentCount = studentService.getStudentCount(searchWord);
		int lastPage = studentCount / rowPerPage;
		if(studentCount % rowPerPage != 0) {
			lastPage++;
		}
		//for문 1:1~10, 2:1~10, 3:1~10... 10: 1~10, 11: 11~20, 12:11~20, ...
		int startPage = currentPage - (currentPage-1) % rowPerPage; 
		int endPage = startPage + rowPerPage - 1;
		
		model.addAttribute("studentList", studentList);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);	
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("rowPerPage", rowPerPage);	
		
		return "/employee/student/studentList";
	}
}
