package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExampleMapper {
	List<Map<String, Object>> selectExampleByQuestion(int questionNo);
}
