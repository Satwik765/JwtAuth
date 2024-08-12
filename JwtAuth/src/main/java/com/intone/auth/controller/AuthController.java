package com.intone.auth.controller;
import com.intone.auth.model.UserRecord;
import com.intone.auth.service.JwtUtil;
import com.intone.auth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="auth")
public class AuthController {
	@Autowired
	public JwtUtil jwt;
	
	@Autowired
	public UserService service;
	
	@GetMapping("/SignUp")
	public String getToken(@RequestParam String userId) {
		String token= jwt.creatToken(userId);
		UserRecord user = new UserRecord(userId, token);
		System.out.println("user object created"+"  UserId:"+user.getUserId()+"  Token:"+user.getToken());
		try {
		service.addUserAndToken(user);}
		catch(Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	@GetMapping("/SignIn")
	/*public String signIn(@RequestParam String userId, @RequestParam String token) {
		//String userIdFromToken = jwt.getUserNameFromToken(token);
		//System.out.println(userIdFromToken);
		String userIdFromRepo =service.getUserRecord(userId).get().getUserId();
		String tokenFromRepo =service.getUserRecord(userId).get().getToken();
		System.out.println(userIdFromRepo);
		System.out.println(tokenFromRepo);
		System.out.println(userId);
		System.out.println(token);
		boolean a= userIdFromRepo.equals(userId);
		boolean b= tokenFromRepo.equals(token);
		
		if( a && b ) {
			String output = "welcome"+userId;
			return output;
		}
		else {
			return "Invalid userId or token";
		}
	}*/
	public String signin(@RequestParam String userId) {
		if(service.getUserRecord(userId).get().getUserId()!=null ) {
			String output = "welcome"+userId;
			return output;
		}
		else {
			return "Invalid userId or token";
		}
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello user";
	}
	
}
