package com.digging.gingstyle.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/login-view")
	public String inputLogin() {
		return "user/login";
	}
	
	@GetMapping("/join-view")
	public String inputJoin() {
		return "user/join";
	}
}
