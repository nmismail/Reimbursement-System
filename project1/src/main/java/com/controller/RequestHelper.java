package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(request.getRequestURI());
		switch(request.getRequestURI()) {
		case "/project1/login.do":
		return LoginController.login(request, response);
		case "/project1/home.do":
		return HomeController.home(request);
		case "/project1/register.do":
		return RegisterController.register(request);
		case "/project1/submitreimb.do":
		return ReimbursmentController.createReimbursement(request);
		case "/project1/updateReimb.do":
		return ReimbursmentController.updateReimbursement(request);
		case "/project1/denyReimb.do":
		return ReimbursmentController.denyReimbursement(request);
		case "/project1/logout.do":
		return LogoutController.logout(request, response);
		
		default:
		return "index.html";
	
	}
}
}
