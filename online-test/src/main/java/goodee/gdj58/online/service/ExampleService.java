package goodee.gdj58.online.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.ExampleMapper;

@Service
@Transactional
public class ExampleService {
	@Autowired
	private ExampleMapper exampleMapper;
	
	// 해당 문제 보기
	public List<Map<String, Object>> getExampleByQuestion(int questionNo) {
		return exampleMapper.selectExampleByQuestion(questionNo);
	}
	
}
