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
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {
	@Autowired
	StudentService studentService;
	@Autowired
	IdService idService;
	
	// 학생 메인 페이지
	@GetMapping("/studentMain")
	public String getTeacherMain() {
		log.debug("\u001B[31m" + "studentMain Form");
		
		return "/student/studentMain";
	}
	
	// 학생 로그인
	@GetMapping("/loginStudent")
	public String loginStudent(HttpSession session) {
		log.debug("\u001B[31m" + "loginStudent Form");
		
		if(session.getAttribute("loginStudent") != null) {
			return "redirect:/studentMain";
		}
		
		return "/student/loginStudent";
	}
	
	@PostMapping("/loginStudent")
	public String loginStudent(HttpSession session
								, Student student) {
		log.debug("\u001B[31m" + "loginStudent Action");
		
		Student resultStudent = studentService.login(student);
		
		if(resultStudent == null) {
			log.debug("\u001B[31m" + "로그인 실패");
			return "redirect:/loginStudent";
		}
		
		log.debug("\u001B[31m" + "로그인 성공");
		session.setAttribute("loginStudent", resultStudent);
		
		return "redirect:/studentMain";
	}
	
	// 학생 로그아웃
	@GetMapping("/student/logout")
	public String logout(HttpSession session) {
		log.debug("\u001B[31m" + "logoutStudent Action");
		
		session.invalidate();
		log.debug("\u001B[31m" + "sessiong invalidate");
		
		return "redirect:/loginStudent";
	}
	
	// 학생 비밀번호 수정
	@GetMapping("/student/modifyStudentPw")
	public String modifyStudentPw() {
		log.debug("\u001B[31m" + "modifyStudentPw Form");
		
		return "/student/modifyStudentPw";
	}
	
	@PostMapping("/student/modifyStudentPw")
	public String modifyStudentPw(HttpSession session
									,Model model
									, @RequestParam(value = "oldPw") String oldPw
									, @RequestParam(value = "newPw") String newPw) {
		log.debug("\u001B[31m" + "modifyStudentPw Action");
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int row = studentService.modifyStudentPw(loginStudent.getStudentNo(), newPw, oldPw);
		
		if(row != 1) {
			log.debug("\u001B[31m" + "학생 비밀번호 수정 실패");
			model.addAttribute("errorMsg", "비밀번호를 확인해주세요.");
			
			return "/student/modifyStudentPw";
		}
		log.debug("\u001B[31m" + "학생 비밀번호 수정 성공");
		
		return "redirect:/student/logout";
	}
	
	// 학생 등록
	@GetMapping("/employee/student/addStudent")
	public String addStudent() {
		log.debug("\u001B[31m" + "addStudent Form");
		
		return "/employee/student/addStudent";
	}
	
	@PostMapping("/employee/student/addStudent")
	public String addStudent(Model model, Student student) {
		log.debug("\u001B[31m" + "addStudent Action");
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(student.getStudentId());
		if(idCheck != null) {
			log.debug("\u001B[31m" + "중복된 학생 ID");
			model.addAttribute("errorMsg", "중복된 ID");
			
			return "/employee/student/addStudent";
		}
		
		int row = studentService.addStudent(student);
		
		if(row != 1) {
			log.debug("\u001B[31m" + "학생 등록 실패");
			model.addAttribute("errorMsg", "system error : 학생 등록 실패");
		}
		log.debug("\u001B[31m" + "학생 등록 성공");
		
		return "redirect:/employee/student/studentList";
	}
						
	// 학생 삭제
	@GetMapping("/employee/student/removeStudent")
	public String removeStudent(@RequestParam(value = "studentNo") int studentNo) {
		log.debug("\u001B[31m" + "removeStudent Action");
		
		int row = studentService.removeStudent(studentNo);
		
		if(row == 1) {
			log.debug("\u001B[31m" + "학생 삭제 성공");
		} else {
			log.debug("\u001B[31m" + "학생 삭제 실패");
		}
		
		return "redirect:/employee/student/studentList";
	}
	
	// 학생 목록
	@GetMapping("/employee/student/studentList")
	public String getStudentList(Model model
									, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
									, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
									, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		log.debug("\u001B[31m" + "studentList Form");
		log.debug("\u001B[31m" + "currentPage : " + currentPage);
		log.debug("\u001B[31m" + "rowPerPage : " + rowPerPage);
		log.debug("\u001B[31m" + "searchWord : " + searchWord);
		
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
