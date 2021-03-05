package uk.ac.ox.ctl.lti13.demo.req_res;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {
	private String email;
	private String password;
	
	@Builder
	public SignUpRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
