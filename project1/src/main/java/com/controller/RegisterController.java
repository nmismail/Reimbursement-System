package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.service.UsersDAOService;
import com.service.impl.UsersDAOServiceImpl;

public class RegisterController {

	public static UsersDAOService uService = new UsersDAOServiceImpl();
	
	public static String register(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		BasicPasswordEncryptor pencrypt = new BasicPasswordEncryptor();
		
		uService.insertUsers(username,pencrypt.encryptPassword(password),firstname,lastname,email);
		return "index.html";
	}
	
	
}
