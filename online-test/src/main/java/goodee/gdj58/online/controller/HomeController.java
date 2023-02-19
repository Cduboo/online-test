package goodee.gdj58.online.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		
		log.debug("\u001B[31m" + "home");
		
		if(session.getAttribute("loginEmployee") != null) {
			return "redirect:/employee/employeeMain";
		}
		
		if(session.getAttribute("loginTeacher") != null) {
			return "redirect:/teacher/teacherMain";
		}
		if(session.getAttribute("loginStudent") != null) {
			return "redirect:/student/studentMain";
		}
		
		return "/login";
	}
}
