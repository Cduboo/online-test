package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.PaperMapper;
import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Test;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestMapper testMapper;
	@Autowired
	private PaperMapper paperMapper;
	
	// 년도별 각 월의 시험 개수
	public List<Map<String, Object>> getTestCountOfMonthsInYear(int year) {
		return testMapper.selectTestCountOfMonthsInYear(year);
	}
	
	// 시험 수정
	public int modifyTest(Test test, int teacherNo) {
		test.setTeacherNo(teacherNo);
		return testMapper.updateTest(test);
	}
	
	// 시험 삭제
	public int removeTest(int testNo, int teacherNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testNo", testNo);
		paramMap.put("teacherNo", teacherNo);
		
		return testMapper.deleteTest(paramMap);
	}
	
	// 시험 등록
	public int addTest(Test test, int teacherNo) {
		test.setTeacherNo(teacherNo);
		return testMapper.insertTest(test);
	}
	
	// 시험 상세
	public Map<String, Object> getTestOne(int testNo, int teacherNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testNo", testNo);
		paramMap.put("teacherNo", teacherNo);
		
		int paperCountByTest = testMapper.selectPaperCountByTest(testNo);
		
		Map<String, Object> testOneMap = testMapper.selectTestOne(paramMap);
		if(testOneMap != null) {
			testOneMap.put("paperCountByTest", paperCountByTest);			
		}
		
		return testOneMap; 
	}
	
	// 시험 목록(강사)
	public List<Map<String, Object>> getTestListByTeacher(int teacherNo) {
		return testMapper.selectTestListByTeacher(teacherNo);
	}
	
	// 시험 목록(학생)
	public List<Map<String, Object>> getTestListByStudent(int studentNo) {
		List<Integer> testResultListByStudent = paperMapper.selectTestCheck(studentNo);
		List<Map<String, Object>> testListByStudent = testMapper.selectTestListByStudent();
		
		// 해당 학생이 치룬 시험 체크
		for(Map<String, Object> m : testListByStudent) {
			for(Integer i : testResultListByStudent) {
				if(i.equals( m.get("testNo"))) {
					m.put("testCk", "testCk");
				}
			}
		}
		
		return testListByStudent;
	}
}
