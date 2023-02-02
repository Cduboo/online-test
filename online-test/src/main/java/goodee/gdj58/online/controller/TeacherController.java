package goodee.gdj58.online.controller;

import java.util.List;

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
		
		int row = teacherService.addStudent(teacher);
		
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
									, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage) {
		log.debug("\u001B[31m" + "teacherList Form");
		
		List<Teacher> teacherList = teacherService.getTeacherList(currentPage, rowPerPage);
		
		model.addAttribute("teacherList", teacherList);
		model.addAttribute("currentPage", currentPage);
		
		return "/employee/teacher/teacherList";
	}
}
