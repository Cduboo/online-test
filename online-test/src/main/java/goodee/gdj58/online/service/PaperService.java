package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.PaperMapper;
import goodee.gdj58.online.vo.Paper;

@Service
@Transactional
public class PaperService {
	@Autowired private PaperMapper paperMapper;
	
	// 시험 결과
	public List<Map<String, Object>> getTestResult(int studentNo, int testNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("studentNo", studentNo);
		paramMap.put("testNo", testNo);
		
		return paperMapper.selectTestResult(paramMap);
	}
	
	// 답안지 제출
	public int addPaper(int[] questionNo, int studentNo, int[] anwser) {
		int row = 0;
		
		Paper paramPaper = new Paper();
		for(int i = 0; i < questionNo.length; i++) {
			paramPaper.setQuestionNo(questionNo[i]);
			paramPaper.setStudentNo(studentNo);
			paramPaper.setAnswer(anwser[i]);
			row = paperMapper.insertPaper(paramPaper);
		}
		
		return row;
	}
}
