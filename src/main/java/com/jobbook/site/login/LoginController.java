package com.jobbook.site.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {
	
	@GetMapping("/login_view")
	public String kakaoLogin() {
				return "login/loginView";
	}

}
