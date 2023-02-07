package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Example;

@Mapper
public interface ExampleMapper {
	int insertExample(Example example);
	List<Map<String, Object>> selectExampleByQuestion(int questionNo);
}
