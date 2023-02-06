package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.QuestionMapper;

@Service
@Transactional
public class QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	
	// 문제 상세
	public List<Map<String, Object>> getQuestionListByTest(int testNo, int teacherNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testNo", testNo);
		paramMap.put("teacherNo", teacherNo);
		
		return questionMapper.selectQuestionListByTest(paramMap); 
	}
}
