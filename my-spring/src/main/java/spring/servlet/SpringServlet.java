package spring.servlet;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spring.applicationcontext.SpringApplicationContext;
import spring.bean.BeanHashMap;
import spring.controller.UserController;


public class SpringServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		SpringApplicationContext applicationContext = 
				(SpringApplicationContext) this.getServletContext()
				.getAttribute("SpringApplicationContext");
		
		UserController userController = 
			(UserController) applicationContext.getBean("userController");
		userController.say();
	}
}
