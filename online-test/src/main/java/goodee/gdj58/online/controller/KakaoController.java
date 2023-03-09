package goodee.gdj58.online.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.online.service.KakaoService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.vo.Student;

@Controller
public class KakaoController {
	@Autowired KakaoService kakaoService;
	@Autowired StudentService studentService;
	
	@GetMapping("/login/kakao")
	public String kakaoLogin(HttpSession session, @RequestParam("code") String code) {
		String accessToken = kakaoService.getKakaoAccessToken(code);
		
		if(accessToken != null) {
			Student userInfo = kakaoService.getKakaoProfile(accessToken);
			if(userInfo != null) {
				int row = kakaoService.addKakaoUser(userInfo);
				if(row == 1) { // 회원가입 성공
					Student loginStudent = studentService.login(userInfo);
					session.setAttribute("loginStudent", loginStudent);
					return "redirect:/student/test/testList";
				} else if(row == -1 ) { // 아이디 중복
					int studentNo = userInfo.getStudentNo();
					String studentId = userInfo.getStudentId();
					String studentName = userInfo.getStudentName();
					
					Student student = new Student();
					student.setStudentNo(studentNo);
					student.setStudentId(studentId);
					student.setStudentName(studentName);
					
					Student loginStudent = student;
					session.setAttribute("loginStudent", loginStudent);
					session.setAttribute("oauth", "oauth");
					return "redirect:/student/test/testList";
				}
			}
		}
		
		return "redirect:/home";
	}
}
