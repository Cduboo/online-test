package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import goodee.gdj58.online.vo.Employee;

@Mapper
public interface EmployeeMapper {
	List<Map<String, Object>> selectRecentEmployeeList();
	Map<String, Object> selectTotalCount();
	int updateEmployeePw(Map<String, Object> paraMap); // 현 비밀번호 새 비밀번호 mybatis는 매개변수 하나만 받는다. 
	Employee login(Employee employee);
	int deleteEmployee(int employeeNo);
	int insertEmployee(Employee employee);
	List<Employee> selectEmployeeList(Map<String, Object> paramMap);
	int selectEmployeeCount(String searchWord);
}