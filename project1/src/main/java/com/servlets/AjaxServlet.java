package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.RequestHelper2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimb.Reimbursment;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Reimbursment> reimbList = RequestHelper2.process(request);
		String string = new ObjectMapper().writeValueAsString(reimbList);
		response.setContentType("application/json");
		response.getWriter().write(string);
	}

	

}
