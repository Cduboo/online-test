package goodee.gdj58.online.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import goodee.gdj58.online.service.QuestionService;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QustionController {	
	@Autowired private QuestionService questionService;
	String logRed = "\u001B[31m";
	
	// 문제 수정
	@PostMapping("/teacher/test/modifyQuestion")
	public String modifyQuestion(HttpSession session, RedirectAttributes re
							, Question question
							, @RequestParam(value = "exampleIdx") int[] exampleIdx
							, @RequestParam(value = "exampleTitle") String[] exampleTitle
							, @RequestParam(value = "exampleNo") int[] exampleNo
							, @RequestParam(value = "exampleOx") String exampleOx) {
		log.debug(logRed + "modifyQuestion Action");
		log.debug(logRed + "testNo : " + question.getTestNo());
		log.debug(logRed + "questionIdx : " + question.getQuestionIdx());
		log.debug(logRed + "questionTitle : " + question.getQuestionTitle());
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		
		int row = questionService.modifyQuestion(question, teacherNo, exampleNo, exampleIdx, exampleTitle, exampleOx);
		
		if(row != 1) {
			log.debug(logRed + "문제/보기 수정 실패");
			re.addFlashAttribute("msg", "MODIFY_ERROR");
		} else if(row == -1) {
			log.debug(logRed + "해당 권한이 없습니다.");
			re.addFlashAttribute("msg", "MODIFY_AUTH_ERROR");
		} else {
			log.debug(logRed + "문제/보기 수정 성공");
			re.addFlashAttribute("msg", "MODIFY_SUCCESS");
		} 
		
		return "redirect:/teacher/test/testDetail?testNo=" + question.getTestNo();
	}
	
	
	// 문제 삭제
	@PostMapping("/teacher/test/removeQuestion")
	public String removeQuestion(HttpSession session, RedirectAttributes re
									, @RequestParam(value = "questionNo") int questionNo
									, @RequestParam(value = "testNo") int testNo) {
		log.debug(logRed + "removeQuestion Action");
		
		Teacher loginTeacher = (Teacher)session.getAttribute("loginTeacher");
		int teacherNo = loginTeacher.getTeacherNo();
		int row = questionService.removeQuestion(questionNo, teacherNo);
		
		if(row != 1) {
			log.debug(logRed + "문제 삭제 실패");
			re.addFlashAttribute("msg", "DELETE_ERROR");
		} else if(row == -1) { 
			log.debug(logRed + "해당 권한이 없습니다.");
			re.addFlashAttribute("msg", "DELETE_AUTH_ERROR");
		} else {
			log.debug(logRed + "문제 삭제 성공");
			re.addFlashAttribute("msg", "DELETE_SUCCESS");
		}
		
		return "redirect:/teacher/test/testDetail?testNo="+testNo;
	}
	
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
