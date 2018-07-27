package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jasypt.util.password.BasicPasswordEncryptor;

import com.reimb.Users;
import com.service.UsersDAOService;
import com.service.impl.UsersDAOServiceImpl;

public class LoginController {

	public static UsersDAOService uService = new UsersDAOServiceImpl();
	public static Logger log = Logger.getLogger(ReimbursmentController.class);

	public static String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Users> user = uService.getAllUsers();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		BasicPasswordEncryptor pencrypt = new BasicPasswordEncryptor();
		
		HttpSession session = request.getSession();
		
		//uService.selectByUsername(username, password);
		
		for (Users u : user) {
			
			if (u.getUsername().equals(username) && pencrypt.checkPassword(password, u.getPassword())) {
				if (u.getRole_id() == 1) {
					Cookie ck = new Cookie("username", username);
					log.info("Username: "+username);
					ck.setMaxAge(0);
		            response.addCookie(ck);
					request.getSession().setAttribute("loggedusername", username);
					request.getSession().setAttribute("loggedpassword", password);
					
					return "financemgrhome.html";
				} else {
					request.getSession().setAttribute("loggedusername", username);
					request.getSession().setAttribute("loggedpassword", password);
					log.info("Username: "+username);
					return "emphome.html";
				}
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><body><h3>Invalid username/pass</h3></body></head></html>");
		
		
		return "index.html";

	}
}
