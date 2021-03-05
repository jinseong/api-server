package uk.ac.ox.ctl.lti13.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.ac.ox.ctl.lti13.demo.req_res.SignInRequest;
import uk.ac.ox.ctl.lti13.demo.req_res.SignInResponse;
import uk.ac.ox.ctl.lti13.demo.req_res.SignUpRequest;
import uk.ac.ox.ctl.lti13.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signUp")
	public String signUp(SignUpRequest signUpRequest) {
		userService.signUp(signUpRequest);
		return "Sign Up OK";
	}
	
	@PostMapping("/signIn")
	public SignInResponse signIn(SignInRequest signInRequest) {
		return userService.signIn(signInRequest);
	}
}
