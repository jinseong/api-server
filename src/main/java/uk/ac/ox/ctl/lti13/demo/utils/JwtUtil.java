package uk.ac.ox.ctl.lti13.demo.utils;

public interface JwtUtil {
	String createToken();
	void verifyToken(String givenToken);
}
