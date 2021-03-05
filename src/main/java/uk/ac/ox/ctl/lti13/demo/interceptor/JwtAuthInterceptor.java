package uk.ac.ox.ctl.lti13.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import uk.ac.ox.ctl.lti13.demo.dto.User;
import uk.ac.ox.ctl.lti13.demo.dto.UserRepository;
import uk.ac.ox.ctl.lti13.demo.utils.JwtUtil;

@Component
public class JwtAuthInterceptor implements HandlerInterceptor{
	
	private final Logger log = LoggerFactory.getLogger(JwtAuthInterceptor.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepository;

	private String HEADER_TOKEN_KEY = "Authorization";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		if(StringUtils.equals(request.getMethod(), "OPTIONS")){
			log.debug("if request options method is options, return true");
			return true;
		}
		
		User user = userRepository.findById(Long.parseLong(request.getHeader("userId")))
								.orElseThrow(() -> new IllegalArgumentException("없는 유저 입니다."));
		
		String givenToken = request.getHeader(HEADER_TOKEN_KEY);
		
		verifyToken(givenToken, user.getToken());
		
		return true;
	}
	
	private void verifyToken(String givenToken, String membersToken) {
		if(! givenToken.equals(membersToken)) {
			throw new IllegalArgumentException("사용자의 Token과 일치하지 않습니다.");
		}
		
		jwtUtil.verifyToken(givenToken);
	}
}
