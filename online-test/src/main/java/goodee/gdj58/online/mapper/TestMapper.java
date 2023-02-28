package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Student;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TestMapper {
	int selectQuestionCountByTest(int testNo);
	List<Student> selectStudentByTest(int testNo);
	List<Map<String, Object>> selectTestCountOfMonthsInYear(int year);
	int selectPaperCountByTest(int testNo);
	int updateTest(Test test);
	int deleteTest(Map<String, Object> paramMap);
	int insertTest(Test test);
	Map<String, Object> selectTestOne(Map<String, Object> paramMap);
	List<Map<String, Object>> selectTestListByTeacher(int teacherNo);
	List<Map<String, Object>> selectTestListByStudent();
}
