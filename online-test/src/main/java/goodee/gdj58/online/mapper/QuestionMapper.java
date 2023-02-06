package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
	List<Map<String, Object>> selectQuestionListByTest(Map<String, Object> paramMap);
}
