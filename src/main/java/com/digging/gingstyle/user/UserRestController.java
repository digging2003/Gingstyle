package com.digging.gingstyle.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digging.gingstyle.user.domain.User;
import com.digging.gingstyle.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	// 중복 확인 API
	@GetMapping("/duplicate-email")
	public Map<String, Boolean> duplicate(@RequestParam String email) {
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		User duplicateUser = userService.duplicateEmail(email);
		
		if(duplicateUser != null) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		
		return resultMap;
	}
	
	// 회원 가입 API
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam String name
			, @RequestParam String email
			, @RequestParam String password) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(name, email, password)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 로그인 API 
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam String email
			, @RequestParam String password
			, HttpServletRequest request) {
		
		User user = userService.getUser(email, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			// 성공
			HttpSession session = request.getSession();
			// 로그인 성공 이후 사용자 정보를 session에 저장한다. 
			// session은 특정 클라이언트의 정보를 저장한다.
			// 다른 요청에서도 같은 클라이언트라면 해당 값을 사용할 수 있다.
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userEmail", user.getEmail());
			
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
}
