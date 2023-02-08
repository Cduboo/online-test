package goodee.gdj58.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.ExampleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ExampleController {
	@Autowired
	private ExampleService exampleService;
	String logRed = "\u001B[31m";
	
	// 보기 삭제
	@PostMapping("/teacher/test/removeExample")
	public String removeQuestion(@RequestParam(value = "exampleNo") int exampleNo
									,@RequestParam(value = "testNo") int testNo) {
		log.debug(logRed + "removeExample Action");
	
		int row = exampleService.removeExample(exampleNo);
		if(row != 1) {
			log.debug(logRed + "보기 삭제 실패");
		}
		log.debug(logRed + "보기 삭제 성공");
		
		return "redirect:/teacher/test/testDetail?testNo=" + testNo;
	}
}
