package goodee.gdj58.online.restController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.TestService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestRestController {
	@Autowired TestService testService;
	String logRed = "\u001B[31m";
	
	@GetMapping("/employee/testCountByMonth")
	public List<Map<String, Object>> getTestCountByMonth(@RequestParam(value = "year", defaultValue = "0") int year) {
		log.debug(logRed + "TestRestController");
		
		return testService.getTestCountOfMonthsInYear(year);
	}
}
