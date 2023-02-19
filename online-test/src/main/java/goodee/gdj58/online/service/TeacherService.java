package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TeacherMapper;
import goodee.gdj58.online.vo.Teacher;

@Service
@Transactional
public class TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;
	
	// 강사 로그인
	public Teacher login(Teacher Teacher) {
		return teacherMapper.login(Teacher);
	}
	
	// 강사 비밀번호 수정
	public int modifyTeacherPw(int teacherNo, String newPw, String oldPw) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("newPw", newPw);
		paramMap.put("oldPw", oldPw);
		
		return teacherMapper.modifyTeacherPw(paramMap);
	}
	
	// 강사 등록
	public int addTeacher(Teacher teacher) {
		return teacherMapper.insertTeacher(teacher);
	}
	
	// 강사 삭제
	public int removeTeacher(int teacherNo) {
		String removeTeacherCk = teacherMapper.selectTeacherByTest(teacherNo);
		
		// 등록한 시험이 존재하면 -1
		if(removeTeacherCk != null) {
			return -1;
		}
		
		return teacherMapper.deleteTeacher(teacherNo);
	}
	
	// 강사 목록
	public List<Teacher> getTeacherList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage - 1) * rowPerPage;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		return teacherMapper.selectTeacherList(paramMap);
	}
	
	// 총 강사 수
	public int getTeacherCount(String searchWord) {
		return teacherMapper.selectTeacherCount(searchWord);
	}
}
