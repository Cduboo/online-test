package goodee.gdj58.online.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.IdService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IdCkRestController {
	@Autowired IdService idService;
	String logRed = "\u001B[31m";
	
	@GetMapping("/employee/idCk")
	public String idCk(@RequestParam(value = "id") String id) {
		log.debug(logRed + "idCk RestController"); 
		
		// ID 중복 검사 (null이 아니면 중복)
		String idCheck = idService.getIdCheck(id);
		log.debug(logRed + idCheck);
		
		return idCheck;
	}
}
