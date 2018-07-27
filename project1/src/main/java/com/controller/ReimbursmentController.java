package com.controller;


import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.reimb.Reimbursment;
import com.reimb.ReimbursmentType;
import com.reimb.Users;
import com.service.ReimbursmentDAOService;
import com.service.ReimbursmentTypeDAOService;
import com.service.UsersDAOService;
import com.service.impl.ReimbursmentDAOServiceImpl;
import com.service.impl.ReimbursmentTypeDAOServiceImpl;
import com.service.impl.UsersDAOServiceImpl;


public class ReimbursmentController {

	 public static ReimbursmentDAOService rservice = new ReimbursmentDAOServiceImpl();
	 public static ReimbursmentTypeDAOService typeservice = new ReimbursmentTypeDAOServiceImpl();
	 public static UsersDAOService usersservice = new UsersDAOServiceImpl();
		public static Logger log = Logger.getLogger(ReimbursmentController.class);

	 
    public static String createReimbursement(HttpServletRequest request) {
        
    	Users user = new Users();
        double amount = Double.parseDouble(request.getParameter("amount"));
        Timestamp submitted = new Timestamp(System.currentTimeMillis());
        //Timestamp resolved = new Timestamp(System.currentTimeMillis());
        String description = request.getParameter("description");
        String author = (String) request.getSession().getAttribute("loggedusername");
        Users userss = usersservice.selectByUsername(author);
        log.info("User: "+author);
        System.out.println(author);
        System.out.println(userss);
        int authorID = userss.getUsers_id();
        String name = userss.getFirstname();
        log.info("Description: "+description);
        //System.out.println(authorID);
        //int author = user.getUsers_id();
        System.out.println(request.getParameter("type"));
        String type = request.getParameter("type");
        log.info("Type: "+type);
        ReimbursmentType reimbType = typeservice.getByType(type);
        int typeId = reimbType.getReimb_type_id();
        //int type = Integer.parseInt(request.getParameter("type")); 
        System.out.println(type);
        int resolver = 0;
        int statusid = 0;
        //int type =1;
                
        Reimbursment reimb= new Reimbursment(amount, submitted, null, description, authorID, resolver, statusid, typeId);

        rservice.insertReimbursment(reimb);
        
        return "emphome.html";
    }
       public static String updateReimbursement(HttpServletRequest request) {
    	   int id = Integer.parseInt(request.getParameter("reimbID"));
    	   Reimbursment updatereimb = rservice.selectById(id);
    	   System.out.println(updatereimb);
    	   System.out.println(id);
    	   updatereimb.setReimb_status_id(1);
           Timestamp resolved = new Timestamp(System.currentTimeMillis());
    	   updatereimb.setReimb_resolved(resolved);
    	   String resolver = (String) request.getSession().getAttribute("loggedusername");
    	   Users userss = usersservice.selectByUsername(resolver);
           int resolverID = userss.getUsers_id();
    	   updatereimb.setReimb_resolver(resolverID);
    	   rservice.updateReimb(updatereimb);
    	   
    	  
		return "financemgrhome.html";
    	   
       }
       
       public static String denyReimbursement(HttpServletRequest request) {
    	   int id = Integer.parseInt(request.getParameter("reimbID"));
    	   Reimbursment updatereimb = rservice.selectById(id);
    	   System.out.println(updatereimb);
    	   System.out.println(id);
    	   updatereimb.setReimb_status_id(2);
           Timestamp resolved = new Timestamp(System.currentTimeMillis());
    	   updatereimb.setReimb_resolved(resolved);
    	   String resolver = (String) request.getSession().getAttribute("loggedusername");
    	   Users userss = usersservice.selectByUsername(resolver);
           int resolverID = userss.getUsers_id();
    	   updatereimb.setReimb_resolver(resolverID);
    	   rservice.updateReimb(updatereimb);
    	   
    	   
		return "financemgrhome.html";
    	   
       }
       
      
}


