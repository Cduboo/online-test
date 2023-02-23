package goodee.gdj58.online.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@Autowired TestService testService;
	@Autowired QuestionService questionService;
	
	String logRed = "\u001B[31m";
	
	// 시험 수정
	@GetMapping("/teacher/test/modifyTest")
	public String modifyTest(RedirectAttributes re
								, @RequestParam(value = "testNo", defaultValue = "0" ) int testNo) {
		log.debug(logRed + "modifyTest Form");
		
		re.addFlashAttribute("MOD", "MOD_MODIFY");
		log.debug(logRed + "MOD : " + re.getAttribute("MOD"));
		
		return "redirect:/teacher/test/testDetail?testNo=" + testNo;
	}
	
	@PostMapping("/teacher/test/modifyTest")
	public String modifyTest(HttpSession session, RedirectAttributes re 
								, Test test) {
		log.debug(logRed + "modifyTest Action");
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		
		int row = testService.modifyTest(test, teacherNo);
		if(row != 1) {
			re.addFlashAttribute("msg", "MODIFY_ERROR");
		} else {
			re.addFlashAttribute("msg", "MODIFY_SUCCESS");
		}
		
		return "redirect:/teacher/test/testDetail?testNo=" + test.getTestNo();
	}
	
	
	// 시험 삭제
	@PostMapping("/teacher/test/removeTest")
	public String removeTest(HttpSession session
								, @RequestParam(value = "testNo") int testNo) {
		log.debug(logRed + "removeTest Action");
		log.debug(logRed + "testNo : " + testNo);
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		
		testService.removeTest(testNo, teacherNo);
		
		return "redirect:/teacher/test/testList";
	}
	
	// 시험 등록
	@PostMapping("/teacher/test/addTest")
	public String addTest(HttpSession session, RedirectAttributes re, Test test) {
		log.debug(logRed + "addTest Action");
		log.debug(logRed + "testTitle : " + test.testTitle);
		log.debug(logRed + "testMemo : " + test.testMemo);
		log.debug(logRed + "startDate : " + test.startDate);
		log.debug(logRed + "endDate : " + test.endDate);
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		int row = testService.addTest(test, teacherNo);
		
		if(row != 1) {
			log.debug(logRed + "시험 등록 실패");
			re.addFlashAttribute("msg", "ADD_ERROR");
		} else {
			log.debug(logRed + "시험 등록 성공");
			re.addFlashAttribute("msg", "ADD_SUCCESS");
		}
		
		return "redirect:/teacher/test/testList";
	}
	
	// 시험 상세(강사)
	@GetMapping("/teacher/test/testDetail")
	public String getTestOneByTeacher(HttpSession session
								, Model model
								, @RequestParam(value = "testNo", defaultValue = "0" ) int testNo) {
		log.debug(logRed + "testDetail Form");
		log.debug(logRed + "testNo : " + testNo);
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		
		List<Map<String, Object>> questionList = questionService.getQuestionListOfTestByTeacher(testNo, teacherNo);
		Map<String, Object> testOne = testService.getTestOne(testNo, teacherNo);
		
		if(testOne == null) {
			log.debug(logRed + "해당 권한 없음");
			return "redirect:/teacher/test/testList";
		}
		model.addAttribute("questionList", questionList);
		model.addAttribute("testOne", testOne);
		
		return "/teacher/test/testDetail";
	}
	
	// 시험 상세(학생)
	@GetMapping("/student/test/testDetail")
	public String getTestOneByStudent(HttpSession session
										, Model model
										, @RequestParam(value = "testNo") int testNo) {
		log.debug(logRed + "testDetail Form");
		log.debug(logRed + "testNo : " + testNo);
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		List<Map<String, Object>> questionList = questionService.getQuestionListOfTestByStudent(testNo, studentNo);
		
		if(questionList == null) {
			return "redirect:/student/test/testList";
		}
		
		model.addAttribute("questionList", questionList);
		
		return "/student/test/testDetail";
	}
	
	// 시험 목록(강사)
	@GetMapping("/teacher/test/testList")
	public String getTestListByTeacher(HttpSession session, Model model) {
		log.debug(logRed + "teacher test From");
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		
		List<Map<String, Object>> testList = testService.getTestListByTeacher(teacherNo);
		
		model.addAttribute("testList", testList);
		
		return "/teacher/test/testList";
	}
	
	// 시험 목록(학생)
	@GetMapping("/student/test/testList")
	public String getTestListByStudent(HttpSession session, Model model) {
		log.debug(logRed + "student test From");
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		List<Map<String, Object>> testList = testService.getTestListByStudent(studentNo);
		
		model.addAttribute("testList", testList);
		
		return "/student/test/testList";
	}
}
