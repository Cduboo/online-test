package goodee.gdj58.online.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Question;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QustionController {	
	@Autowired private QuestionService questionService;
	String logRed = "\u001B[31m";
	
	// 문제 등록
	@GetMapping("/teacher/test/addQuestion")
	public String addQuestion() {
		log.debug(logRed + "addQuestion From");
		
		return "/teacher/test/addQuestion";
	}
	
	@PostMapping("/teacher/test/addQuestion")
	public String addQuestion(HttpSession session
								, Question question
								, @RequestParam(value = "exampleIdx") int[] exampleIdx
								, @RequestParam(value = "exampleTitle") String[] exampleTitle
								, @RequestParam(value = "exampleOx") String exampleOx) {
		log.debug(logRed + "addQuestion Action");
		log.debug(logRed + "testNo : " + question.getTestNo());
		log.debug(logRed + "questionIdx : " + question.getQuestionIdx());
		log.debug(logRed + "questionTitle : " + question.getQuestionTitle());
		
		int row = questionService.addQuestion(question, exampleIdx, exampleTitle, exampleOx);
		
		if(row != 1) {
			log.debug(logRed + "문제/보기 등록 실패");
			return "redirect:/teacher/test/testDetail?testNo=" + question.getTestNo();
		}
		log.debug(logRed + "문제/보기 등록 성공");
		
		return "redirect:/teacher/test/testDetail?testNo=" + question.getTestNo();
	}
}
