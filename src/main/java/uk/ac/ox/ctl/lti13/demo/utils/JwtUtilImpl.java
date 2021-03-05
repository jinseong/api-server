package uk.ac.ox.ctl.lti13.demo.utils;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtilImpl implements JwtUtil{
	private String TEST_SIGN_KEY = "TESTKEY";
	private String ISSUER = "JJS";
	
	@Override
	public String createToken() {
		return JWT.create()
				.withIssuer(ISSUER)
				.sign(Algorithm.HMAC256(TEST_SIGN_KEY));
	}
	
	@Override
	public void verifyToken(String givenToken) {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TEST_SIGN_KEY))
				.withIssuer(ISSUER)
				.build();
		
		verifier.verify(givenToken);
	}
}
