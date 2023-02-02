package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.EmployeeMapper;
import goodee.gdj58.online.vo.Employee;

@Service
@Transactional
public class EmployeeService {
	// Autowired - DI, 타입으로 의존성 주입, 스프링에서 빈 인스턴스가 생성된 이후 @Autowired를 설정한 메서드가 자동으로 호출되고, 인스턴스가 자동으로 주입
	@Autowired 
	private EmployeeMapper employeeMapper;
	
	// 직원 비밀번호 변경
	public int modifyEmployeePw(int employeeNo, String oldPw, String newPw) { // service단에서 Map 가공 
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("employeeNo", employeeNo);
		paraMap.put("oldPw", oldPw);
		paraMap.put("newPw", newPw);
		
		return employeeMapper.updateEmployeePw(paraMap);
	}
	
	// 직원 로그인
	public Employee login(Employee employee) {
		return employeeMapper.login(employee);
	}
	
	// 직원 삭제
	public int removeEmployee(int employeeNo) {
		return employeeMapper.deleteEmployee(employeeNo);
	}
	
	// 직원 등록
	public int addEmployee(Employee employee) {
		return employeeMapper.insertEmployee(employee);
	}
	
	// 직원 목록
	public List<Employee> getEmployeeList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1) * rowPerPage;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		
		return employeeMapper.selectEmployeeList(paramMap);
	}
	
	// 총 직원 수
	public int getEmployeeCount(String searchWord) {
		return employeeMapper.selectEmployeeCount(searchWord);
	}
}
