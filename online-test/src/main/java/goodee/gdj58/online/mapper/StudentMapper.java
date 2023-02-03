package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Student;

@Mapper
public interface StudentMapper {
	Student login(Student student);
	int modifyStudentPw(Map<String, Object> paraMap);
	int insertStudent(Student student);
	int deleteStudent(int studentNo);
	List<Student> selectStudentList(Map<String, Object> paramMap);
	int selectStudentCount(String searchWord);
}
