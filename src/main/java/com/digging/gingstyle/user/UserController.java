package com.digging.gingstyle.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping("/login-view")
	public String inputLogin(HttpSession session) {
		if(session.getAttribute("userId") != null) {
			return "user/myPage";
		}
		return "user/login";
	}
	
	@GetMapping("/join-view")
	public String inputJoin(HttpSession session) {
		if(session.getAttribute("userId") != null) {
			return "user/myPage";
		}
		return "user/join";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userEmail");
		
		return "product/category";
		
	}
}
