package goodee.gdj58.online.restController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.EmployeeService;
import goodee.gdj58.online.service.StudentService;
import goodee.gdj58.online.service.TeacherService;
import goodee.gdj58.online.vo.Employee;
import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Teacher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SearchRestController {
	@Autowired EmployeeService employeeService;
	@Autowired TeacherService teacherService;
	@Autowired StudentService studentService;
	String logRed = "\u001B[31m";
	
	// 직원 목록 검색
	@GetMapping("/employee/searchEmployeeList")
	public List<Employee> employeeList(HttpSession session, Model model 
											, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
											, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
											, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		log.debug(logRed + "searchEmployeeList rest Form");
		log.debug(logRed + "currentPage : " + currentPage);
		log.debug(logRed + "rowPerPage : " + rowPerPage);
		log.debug(logRed + "searchWord : " + searchWord);
		
		List<Employee> employeeList = employeeService.getEmployeeList(currentPage, rowPerPage, searchWord);
		
		return employeeList;
	}
	
	// 강사 목록 검색
	@GetMapping("/employee/searchTeacherList")
	public List<Teacher> teacherList(HttpSession session, Model model 
			, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
			, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		log.debug(logRed + "searchTeacherList rest Form");
		log.debug(logRed + "currentPage : " + currentPage);
		log.debug(logRed + "rowPerPage : " + rowPerPage);
		log.debug(logRed + "searchWord : " + searchWord);
		
		List<Teacher> teacherList = teacherService.getTeacherList(currentPage, rowPerPage, searchWord);
		
		return teacherList;
	}
	
	// 학생 목록 검색
	@GetMapping("/employee/searchStudentList")
	public List<Student> studentList(HttpSession session, Model model 
			, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			, @RequestParam(value = "rowPerPage", defaultValue = "10") int rowPerPage
			, @RequestParam(value = "searchWord", defaultValue = "") String searchWord) {
		log.debug(logRed + "searchStudentList rest Form");
		log.debug(logRed + "currentPage : " + currentPage);
		log.debug(logRed + "rowPerPage : " + rowPerPage);
		log.debug(logRed + "searchWord : " + searchWord);
		
		List<Student> studentList = studentService.getStudentList(currentPage, rowPerPage, searchWord);
		
		return studentList;
	}
}
