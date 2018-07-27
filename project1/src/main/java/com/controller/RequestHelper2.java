package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.reimb.Reimbursment;

public class RequestHelper2 {

public static List<Reimbursment> process(HttpServletRequest request){
		
		switch(request.getRequestURI()) {
		case "/project1/html/getReimbursments.d":
			return AjaxController.getReimbursments(request);
			
		case "/project1/html/getFinanceReimbursments.d":
			return AjaxController.getFinanceReimbursments(request);
		
		case "/project1/html/filterApproved.d":
			return AjaxController.filterApproved(request);
			
			default:
				return null;
}
}
}
