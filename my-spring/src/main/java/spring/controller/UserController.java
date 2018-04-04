package spring.controller;

import spring.service.UserService;

public class UserController {
	
	private UserService userService;


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void say() {
		System.out.println("userController is say");
		userService.say();
	}
	
	

}
