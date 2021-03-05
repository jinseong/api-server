package uk.ac.ox.ctl.lti13.demo.req_res;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInResponse {
	private Long userId;
	private String token;
	
	@Builder
	public SignInResponse(Long userId, String token) {
		this.userId = userId;
		this.token = token;
	}

}
