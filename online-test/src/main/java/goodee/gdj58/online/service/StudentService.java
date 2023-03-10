package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.StudentMapper;
import goodee.gdj58.online.vo.Student;

@Service
@Transactional
public class StudentService {
	@Autowired private StudentMapper studentMapper;
	
	// 학생 로그인
	public Student login(Student student) {
		return studentMapper.login(student);
	}
	
	// 학생 비밀번호 수정
	public int modifyStudentPw(int studentNo, String newPw, String oldPw) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("studentNo", studentNo);
		paramMap.put("newPw", newPw);
		paramMap.put("oldPw", oldPw);
		
		return studentMapper.modifyStudentPw(paramMap);
	}
	
	// 학생 등록
	public int addStudent(Student student) {
		return studentMapper.insertStudent(student);
	}
	
	// 학생 삭제
	public int removeStudent(int studentNo) {
		String removeStudentCk = studentMapper.selectStudentByPaper(studentNo);
		
		// 제출한 답안지가 존재하면 -1
		if(removeStudentCk != null) {
			return -1;
		}
		return studentMapper.deleteStudent(studentNo);
	}
	
	// 학생 목록
	public List<Student> getStudentList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		return studentMapper.selectStudentList(paramMap);
	}
	
	// 총 학생 수
	public int getStudentCount(String searchWord) {
		return studentMapper.selectStudentCount(searchWord);
	}
}
