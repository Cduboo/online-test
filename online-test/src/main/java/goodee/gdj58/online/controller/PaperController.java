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

import goodee.gdj58.online.service.PaperService;
import goodee.gdj58.online.vo.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaperController {
	@Autowired private PaperService paperService;
	String logRed = "\u001B[31m";
	
	// 시험 결과
	@GetMapping("/student/test/testResult")
	public String getTestResult(HttpSession session
									, Model model
									, @RequestParam(value = "testNo", defaultValue = "0" ) int testNo) {
		log.debug(logRed + "testResult Form");
		log.debug(logRed + "testNo : " + testNo);
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		
		List<Map<String, Object>> testResult = paperService.getTestResult(studentNo, testNo);
		model.addAttribute("testResult", testResult);
		
		return "/student/test/testResult";
	}
	
	
	// 답안지 저장
	@PostMapping("/student/test/addPaper")
	public String addPaper(HttpSession session
								, @RequestParam(value = "questionNo") int[] questionNo
								, @RequestParam(value = "answer") int[] answer
								, @RequestParam(value = "testNo") int testNo
								) {
		log.debug(logRed + "addPaper Action");
		log.debug(logRed + "questionNo : " + questionNo);
		log.debug(logRed + "answer : " + answer);
		
		Student loginStudent = (Student)session.getAttribute("loginStudent");
		int studentNo = loginStudent.getStudentNo();
		int row = paperService.addPaper(questionNo, studentNo, answer);
		
		if(row == 0) {
			log.debug(logRed + "답안지 등록 실패");
		} else {
			log.debug(logRed + "답안지 등록 성공");		
		}
		
		return "redirect:/student/test/testList";
	}
}
