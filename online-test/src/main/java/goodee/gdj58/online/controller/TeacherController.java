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
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	@Autowired
	IdService idService;
	
	// 강사 메인 페이지
	@GetMapping("/teacherMain")
	public String getTeacherMain() {
		log.debug("\u001B[31m" + "teacherMain Form");
		
		return "/teacher/teacherMain";
	}
	
	// 강사 로그인
	@GetMapping("/loginTeacher")
	public String loginTeacher(HttpSession session) {
		log.debug("\u001B[31m" + "loginTeacher Form");
		
		if(session.getAttribute("loginTeacher") != null) {
			return "redirect:/teacherMain";
		}
		
		return "/teacher/loginTeacher";
	}
	
	@PostMapping("/loginTeacher")
	public String loginTeacher(HttpSession session
								, Teacher teacher) {
		log.debug("\u001B[31m" + "loginStudent Action");
		
		Teacher resultTeacher = teacherService.login(teacher);
		
		if(resultTeacher == null) {
			log.debug("\u001B[31m" + "로그인 실패");
			return "redirect:/loginTeacher";
		}
		
		log.debug("\u001B[31m" + "로그인 성공");
		session.setAttribute("loginTeacher", resultTeacher);
		
		return "redirect:/teacherMain";
	}
	
	// 강사 로그아웃
	@GetMapping("/teacher/logout")
	public String logout(HttpSession session) {
		log.debug("\u001B[31m" + "logoutTeacher Action");
		
		session.invalidate();
		log.debug("\u001B[31m" + "sessiong invalidate");
		
		return "redirect:/loginTeacher";
	}
	
	// 강서 비밀번호 수정
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw() {
		log.debug("\u001B[31m" + "modifyTeacherPw Form");
		
		return "/teacher/modifyTeacherPw";
	}
	
	@PostMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpSession session
									,Model model
									, @RequestParam(value = "oldPw") String oldPw
									, @RequestParam(value = "newPw") String newPw) {
		log.debug("\u001B[31m" + "modifyTeacherPw Action");
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int row = teacherService.modifyTeacherPw(loginTeacher.getTeacherNo(), newPw, oldPw);
		
		if(row != 1) {
			log.debug("\u001B[31m" + "강사 비밀번호 수정 실패");
			model.addAttribute("errorMsg", "비밀번호를 확인해주세요.");
			
			return "/student/modifyStudentPw";
		}
		log.debug("\u001B[31m" + "강사 비밀번호 수정 성공");
		
		return "redirect:/teacher/logout";
	}
	
	// 강사 등록
	@GetMapping("/employee/teacher/addTeacher")
	public String addTeacher() {
		log.debug("\u001B[31m" + "addTeacher Form");
		
		return "/employee/teacher/addTeacher";
	}
	
	@PostMapping("/employee/teacher/addTeacher")
	public String addStudent(Model model, Teacher teacher) {
		log.debug("\u001B[31m" + "addTeacher Action");
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(teacher.getTeacherId());
		if(idCheck != null) {
			log.debug("\u001B[31m" + "중복된 강사 ID");
			model.addAttribute("errorMsg", "중복된 ID");
			
			return "/employee/teacher/addTeacher";
		}
		
		int row = teacherService.addTeacher(teacher);
		
		if(row != 1) {
			log.debug("\u001B[31m" + "강사 등록 실패");
			model.addAttribute("errorMsg", "system error : 강사 등록 실패");
		}
		log.debug("\u001B[31m" + "강사 등록 성공");
		
		return "redirect:/employee/teacher/teacherList";
	}
	
	// 강사 삭제
	@GetMapping("/employee/teacher/removeTeacher")
	public String removeTeacher(@RequestParam(value = "teacherNo") int teacherNo) {
		log.debug("\u001B[31m" + "removeTeacher Action");
		
		int row = teacherService.removeTeacher(teacherNo);
		
		if(row == 1) {
			log.debug("\u001B[31m" + "강사 삭제 성공");
		} else {
			log.debug("\u001B[31m" + "강사 삭제 실패");
		}
		
		return "redirect:/employee/teacher/teacherList";
	}
	
	// 강사 목록
	@GetMapping("/employee/teacher/teacherList")
	public String getTeacherList(Model model
									, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
									, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
									, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		log.debug("\u001B[31m" + "teacherList Form");
		log.debug("\u001B[31m" + "currentPage : " + currentPage);
		log.debug("\u001B[31m" + "rowPerPage : " + rowPerPage);
		log.debug("\u001B[31m" + "searchWord : " + searchWord);
		
		List<Teacher> teacherList = teacherService.getTeacherList(currentPage, rowPerPage, searchWord);
		int teacherCount = teacherService.getTeacherCount(searchWord);
		int lastPage = teacherCount / rowPerPage;
		if(teacherCount % rowPerPage != 0) {
			lastPage++;
		}
		//for문 1:1~10, 2:1~10, 3:1~10... 10: 1~10, 11: 11~20, 12:11~20, ...
		int startPage = currentPage - (currentPage-1) % rowPerPage; 
		int endPage = startPage + rowPerPage - 1;
		
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);	
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("rowPerPage", rowPerPage);	
		
		return "/employee/teacher/teacherList";
	}
}
