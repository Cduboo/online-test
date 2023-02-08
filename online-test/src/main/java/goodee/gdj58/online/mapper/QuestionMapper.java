package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Question;

@Mapper
public interface QuestionMapper {
	int deleteQuestion(int questionNo);
	int selectMaxQuestionNo();
	int insertQuestion(Question question);
	List<Map<String, Object>> selectQuestionListByTest(Map<String, Object> paramMap);
}
