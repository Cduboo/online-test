package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;
import goodee.gdj58.online.mapper.PaperMapper;
import goodee.gdj58.online.mapper.QuestionMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;

@Service
@Transactional
public class QuestionService {
	@Autowired private QuestionMapper questionMapper;
	@Autowired private ExampleMapper exampleMapper;
	@Autowired private PaperMapper paperMapper;
	
	// 문제 수정
	public int modifyQuestion(Question question,
								int teacherNo, int exampleNo[], int[] exampleIdx, String[] exampleTitle, String exampleOx) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("questionNo", question.getQuestionNo());
		// 본인 문제 확인
		int row = questionMapper.selectTeacherCkByQuestion(paramMap);
		if(row == 0) {
			return -1;
		}
		
		// 문제 수정
		row = questionMapper.updateQuestion(question);
		
		if(row == 1) {
			for(int i = 0; i < exampleIdx.length; i++) {
				Example example = new Example();
				example.setExampleNo(exampleNo[i]);
				example.setExampleIdx(exampleIdx[i]);
				example.setExampleTitle(exampleTitle[i]);
				example.setExampleOx("오답");
				
				if(i == Integer.parseInt(exampleOx)) {
					example.setExampleOx("정답");
				}
				
				row = exampleMapper.updateExample(example);
			}
		}
		
		return row;
	}
	
	// 문제 삭제
	public int removeQuestion(int questionNo, int teacherNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("questionNo", questionNo);
		// 본인 문제 확인
		int row = questionMapper.selectTeacherCkByQuestion(paramMap);
		if(row == 0) {
			return -1;
		}
		
		row = exampleMapper.deleteExample(questionNo);
		if(row == 0) {
			return -1;
		}
		
		return questionMapper.deleteQuestion(questionNo);
	}
	
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
	
	// 문제 상세(강사)
	public List<Map<String, Object>> getQuestionListOfTestByTeacher(int testNo, int teacherNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testNo", testNo);
		paramMap.put("teacherNo", teacherNo);
		
		return questionMapper.selectQuestionListByTest(paramMap); 
	}
	
	// 문제 상세(학생)
	public List<Map<String, Object>> getQuestionListOfTestByStudent(int testNo, int studentNo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("testNo", testNo);
		paramMap.put("studentNo", studentNo);
		
		// 치룬 시험인지 검사
		int row = paperMapper.selectPaperCkByStudent(paramMap);
		System.out.println(row);
		if(row > 0) {
			return null;
		}
		
		return questionMapper.selectQuestionListByTest(paramMap); 
	}
}
