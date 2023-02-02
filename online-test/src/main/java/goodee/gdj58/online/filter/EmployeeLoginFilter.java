package goodee.gdj58.online.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j // static Log log = new Log();
@WebFilter("/employee/*")
public class EmployeeLoginFilter implements Filter{ // 매 컨트롤러마다 분기되는 똑같은 코드가 나와서 필터로 처리해보기
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/*
		 * ServletRequest : 모든 요청을 받는다.
		 * HttpServletRequest : (ServletRequest의 자식) http프로토콜로 들어온 요청(웹 요청)만 받는다. 
		 */
		
		log.debug("\u001B[31m" + "EmployeeLoginFilter");
		
		if(request instanceof HttpServletRequest) { // HttpServletRequest로 형변환 가능한지 검사
			// session은 HttpServletRequest에서 얻어올 수 있다.
			// 따라서, request를 HttpServletRequest로 형변환
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			
			// 직원 비로그인 ---> 로그인 페이지
			if(session.getAttribute("loginEmployee") == null) {
				log.debug("\u001B[31m" + "EmployeeLoginFilter : loginEmployee is NULL");
				((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/loginEmployee"); // 로그인(직원,강사,학생) 페이지
				return;
			}
		} else {
			log.debug("웹 브라우저 요청만 허용합니다."); // @Slf4j
			return;
		}
		
		chain.doFilter(request, response);
	}
	
}
