package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Student;

@Mapper
public interface StudentMapper {
	List<Map<String, Object>> selectRecentStudentList();
	Student login(Student student);
	int modifyStudentPw(Map<String, Object> paraMap);
	int insertStudent(Student student);
	int deleteStudent(int studentNo);
	String selectStudentByPaper(int studentNo);
	List<Student> selectStudentList(Map<String, Object> paramMap);
	int selectStudentCount(String searchWord);
}
