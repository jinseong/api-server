package uk.ac.ox.ctl.lti13.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import uk.ac.ox.ctl.lti13.demo.interceptor.JwtAuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	JwtAuthInterceptor jwtAuthInterceptor; 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtAuthInterceptor)
				.addPathPatterns("/member")
				;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("https://localhost:8443")
				.exposedHeaders("Authorization", "userId")	//make client read header("Authorization")
				;
	}

}
