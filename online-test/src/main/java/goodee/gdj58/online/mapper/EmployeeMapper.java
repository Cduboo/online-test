package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import goodee.gdj58.online.vo.Employee;

@Mapper
public interface EmployeeMapper {
	Employee login(Employee employee);
	int deleteEmployee(int employeeNo);
	int insertEmployee(Employee employee);
	List<Employee> selectEmployeeList(Map<String, Object> paramMap);
}