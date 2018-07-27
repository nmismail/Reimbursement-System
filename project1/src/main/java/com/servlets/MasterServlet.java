package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.controller.RequestHelper;

public class MasterServlet extends HttpServlet {
		
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request,response);
//		  request.getSession().invalidate();
//	        response.sendRedirect(request.getContextPath() + "index.html");
//	        response.setHeader("Cache-Control","no-cache");   
//	        response.setHeader("Cache-Control","no-store");  
//	        response.setDateHeader("Expires", 0);
//	        response.setHeader("Pragma","no-cache");
	        
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.getRequestDispatcher(RequestHelper.process(request, response)).forward(request,response);

}
}

