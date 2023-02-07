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

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.service.TestService;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	@Autowired TestService testService;
	@Autowired QuestionService questionService;
	@Autowired ExampleMapper exampleMapper;
	
	String logRed = "\u001B[31m";
	
	// 시험 삭제
	@GetMapping("/teacher/test/removeTest")
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
	@GetMapping("/teacher/test/addTest")
	public String addTest() {
		log.debug(logRed + "addTest From");
		
		return "/teacher/test/addTest";
	}
	
	@PostMapping("/teacher/test/addTest")
	public String addTest(Model model, Test test) {
		log.debug(logRed + "addTest Action");
		log.debug(logRed + "testTitle : " + test.testTitle);
		log.debug(logRed + "testMemo : " + test.testMemo);
		log.debug(logRed + "startDate : " + test.startDate);
		log.debug(logRed + "endDate : " + test.endDate);
		log.debug(logRed + "teacherNo : " + test.teacherNo);
		
		int row = testService.addTest(test);
		
		if(row != 1) {
			log.debug(logRed + "시험 등록 실패");
			model.addAttribute("errorMsg", "syste error : 시험 등록 실패");
		}
		log.debug(logRed + "시험 등록 성공");
		
		return "redirect:/teacher/test/testList";
	}
	
	// 시험 상세
	@GetMapping("/teacher/test/testDetail")
	public String getTestOne(HttpSession session
								, Model model
								, @RequestParam(value = "testNo") int testNo) {
		log.debug(logRed + "testDetail Form");
		log.debug(logRed + "testNo : " + testNo);
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		
		List<Map<String, Object>> questionList = questionService.getQuestionListByTest(testNo, teacherNo);
		Map<String, Object> testOne = testService.getTestOne(testNo, teacherNo);
		
		model.addAttribute("questionList", questionList);
		model.addAttribute("testOne", testOne);
		
		return "/teacher/test/testDetail";
	}
	
	// 시험 목록
	@GetMapping("/teacher/test/testList")
	public String getTestList(HttpSession session, Model model) {
		log.debug(logRed + "test From");
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		
		List<Map<String, Object>> testList = testService.getTestListByTeacher(teacherNo);
		
		model.addAttribute("testList", testList);
		
		return "/teacher/test/testList";
	}
}
