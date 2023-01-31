package goodee.gdj58.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("")
public class HelloController {
	
	@GetMapping("/test")
	public String test() { // /abc/123 === @GetMapping("/abc/123") 
		System.out.println("String test...");
		
		return "test";
	}
	
	/*
	@GetMapping("/test") 
	public void test() { // 반환 타입이 void -> 맵핑명이 뷰이름이 된다. 
		System.out.println("void test...");
	}
	
	@GetMapping("/test")
	public ModelAndView test() {
		System.out.println("ModelAndView test...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test");
		
		return mv;
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET) // 예전 방식, 하지만 두번째 인자를 안적으면 GET POST 둘다 처리 가능 => @RequestMapping(value="/test2")
	public String test2() {
		System.out.println("test...");
		
		return "test";
	}
	*/
}
