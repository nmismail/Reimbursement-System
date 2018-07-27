package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.reimb.Reimbursment;
import com.reimb.Users;
import com.service.ReimbursmentDAOService;
import com.service.ReimbursmentTypeDAOService;
import com.service.UsersDAOService;
import com.service.impl.ReimbursmentDAOServiceImpl;
import com.service.impl.ReimbursmentTypeDAOServiceImpl;
import com.service.impl.UsersDAOServiceImpl;

public class AjaxController {
	
	 public static ReimbursmentDAOService rservice = new ReimbursmentDAOServiceImpl();
	 public static ReimbursmentTypeDAOService typeservice = new ReimbursmentTypeDAOServiceImpl();
	 public static UsersDAOService usersservice = new UsersDAOServiceImpl();

	public static List<Reimbursment> getReimbursments(HttpServletRequest request) {
		String author = (String) request.getSession().getAttribute("loggedusername");
		Users user = usersservice.selectByUsername(author);
		int authorId = user.getUsers_id();
		String name = user.getFirstname();
		
		List<Reimbursment> reimbList = rservice.selectByAuthor(authorId);
		return reimbList;
	}

	public static List<Reimbursment> getFinanceReimbursments(HttpServletRequest request) {
		
		List<Reimbursment> reimbList = rservice.getAllReimb();
		return reimbList;
	}
	
	public static List<Reimbursment> filterApproved(HttpServletRequest request){
	       
	       List<Reimbursment> list = rservice.selectbyStatus(1);
	       
	       return list;
	       
}
}
	
	
	

