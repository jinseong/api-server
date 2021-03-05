package uk.ac.ox.ctl.lti13.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.ac.ox.ctl.lti13.demo.dto.Member;

@RestController
public class MembersController {

	//@CrossOrigin(origins="https://localhost:8443")
	@GetMapping(path = "/member")
	public Member memberTest() {
		Member testMember = new Member();
		testMember.setId(1);
		testMember.setName("testName");
		testMember.setAge(15);
		testMember.setDept("test");
		
		return testMember;
	}
}
