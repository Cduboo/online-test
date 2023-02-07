package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;

@Service
@Transactional
public class QuestionService {
	@Autowired private QuestionMapper questionMapper;
	@Autowired private ExampleMapper exampleMapper;
	
	// 가장 최근 문제 코드
	public int getMaxQuestionNo() {
		return questionMapper.selectMaxQuestionNo();
	}
	
	// 문제 등록
	public int addQuestion(Question question
								, int[] exampleIdx, String[] exampleTitle, String exampleOx) {
		
		int row = questionMapper.insertQuestion(question);
		
		if(row == 1) {
			int questionNo = questionMapper.selectMaxQuestionNo();
			for(int i = 0; i < exampleIdx.length; i++) {
				Example example = new Example();
				example.setQuestionNo(questionNo);
				example.setExampleIdx(exampleIdx[i]);
				example.setExampleTitle(exampleTitle[i]);
				example.setExampleOx("오답");
				
				if(i == Integer.parseInt(exampleOx)) {
					example.setExampleOx("정답");
				}
				
				row = exampleMapper.insertExample(example);
			}			
		}
		
		return row;
	}
	
	// 문제 상세
	public List<Map<String, Object>> getQuestionListByTest(int testNo, int teacherNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testNo", testNo);
		paramMap.put("teacherNo", teacherNo);
		
		return questionMapper.selectQuestionListByTest(paramMap); 
	}
}
