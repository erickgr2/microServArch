package com.baeldung.userarch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {
	
	@RequestMapping("/login")
	public String login(@RequestParam("userId") String userId, @RequestParam("password") String password){
		String loginStatus;
		if(userId.equals("1234") & password.equals("microservices1")){
			loginStatus = "Success";
			
		}else{
			loginStatus = "Fail";
		}
		
		return loginStatus;
	}

}
