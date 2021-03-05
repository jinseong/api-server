package uk.ac.ox.ctl.lti13.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ox.ctl.lti13.demo.dto.User;
import uk.ac.ox.ctl.lti13.demo.dto.UserRepository;
import uk.ac.ox.ctl.lti13.demo.req_res.SignInRequest;
import uk.ac.ox.ctl.lti13.demo.req_res.SignInResponse;
import uk.ac.ox.ctl.lti13.demo.req_res.SignUpRequest;
import uk.ac.ox.ctl.lti13.demo.utils.JwtUtil;

@Service
public class UserService {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepository;
	
	public void signUp(SignUpRequest signUpRequest) {
		System.out.println("signUpRequest.getEmail() : " + signUpRequest.getEmail());
		
		verifyDuplicatedUser(signUpRequest.getEmail());
		
		User newUser = User.builder()
				.userEmail(signUpRequest.getEmail())
				.userPassword(signUpRequest.getPassword())
				.token(jwtUtil.createToken())
				.build();
		
		userRepository.save(newUser);
	}
	
	private void verifyDuplicatedUser(String userEmail) {
		System.out.println("userRepository.findByUserEmail(userEmail) : " + userRepository.findByUserEmail(userEmail));
		
		if(userRepository.findByUserEmail(userEmail).isPresent()) {
			throw new IllegalArgumentException("중복된 유저입니다.");
			
		}
	}
	
	public SignInResponse signIn(SignInRequest signInRequest) {
		User findUser = userRepository.findByUserEmail(signInRequest.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));
		
		if(! findUser.getUserPassword().equals(signInRequest.getPassword())) {
			throw new IllegalArgumentException("암호가 일치하지 않습니다.");
		}
		
		return new SignInResponse(findUser.getUserId(), findUser.getToken());
	}
	
}
