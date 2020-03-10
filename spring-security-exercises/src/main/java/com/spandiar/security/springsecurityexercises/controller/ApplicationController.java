package com.spandiar.security.springsecurityexercises.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/api/site")
public class ApplicationController {
	
	@GetMapping("/about")
	public String welcome() {
		return "Welcome!!!";
	}
	
	@GetMapping("/user")
	public String user() {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		return "Hello User - " + sessionId;
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Hello Admin";
	}
	
	@GetMapping("/wifey")
	public String wifey() {
		return ("<h2>Hello Wifey</h2>");
	}

}
