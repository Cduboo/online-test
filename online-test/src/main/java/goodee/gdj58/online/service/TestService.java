package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TestMapper;
import goodee.gdj58.online.vo.Test;

@Service
@Transactional
public class TestService {
	@Autowired
	private TestMapper testMapper;
	
	// 시험 등록
	public int addTest(Test test) {
		return testMapper.insertTest(test);
	}
	
	// 시험 상세
	public Map<String, Object> getTestOne(int testNo, int teacherNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testNo", testNo);
		paramMap.put("teacherNo", teacherNo);
		
		return testMapper.selectTestOne(paramMap);
	}
	
	// 시험 리스트
	public List<Map<String, Object>> getTestListByTeacher(int teacherNo) {
		return testMapper.selectTestListByTeacher(teacherNo);
	}
}
