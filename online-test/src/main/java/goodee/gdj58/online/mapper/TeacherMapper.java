package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;

@Mapper
public interface TeacherMapper {
	List<Map<String, Object>> selectRecentTeacherList();
	Teacher login(Teacher student);
	int modifyTeacherPw(Map<String, Object> paraMap);
	int insertTeacher(Teacher teacher);
	int deleteTeacher(int teacherNo);
	String selectTeacherByTest(int teacherNo);
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);
	int selectTeacherCount(String searchWord);
}
