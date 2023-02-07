package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestMapper {
	int deleteTest(Map<String, Object> paramMap);
	int insertTest(Test test);
	Map<String, Object> selectTestOne(Map<String, Object> paramMap);
	List<Map<String, Object>> selectTestListByTeacher(int teacherNo);
}
