package com.controller;

import javax.servlet.http.HttpServletRequest;

public class HomeController {

	public static String home(HttpServletRequest request) {
	
		return "index.html";
		
	}
}
