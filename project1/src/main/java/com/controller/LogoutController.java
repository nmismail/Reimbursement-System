package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController {

	public static String logout(HttpServletRequest request, HttpServletResponse response) {
		
		 HttpSession session = request.getSession();
		
		 if(request.getSession().getAttribute("loggedusername")!=null) {
				session.invalidate();
			}
		Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    loginCookie = cookie;
                    System.out.println("YES"+loginCookie);
                    break;
                }
            }
        }
        System.out.println("YES"+loginCookie);
        if (loginCookie != null) {
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
            
            System.out.println("YES"+loginCookie);
        }
        
        
        
        //request.getSession().invalidate();
		return "index.html";
		
		
		
	}
}
