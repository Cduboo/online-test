package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Paper;

@Mapper
public interface PaperMapper {
	int selectPaperCkByStudent(Map<String, Object> paramMap);
	List<Map<String, Object>> selectTestResult(Map<String, Object> paramMap);
	List<Integer> selectTestCheck(int studentNo);
	int insertPaper(Paper paramPaper);
}