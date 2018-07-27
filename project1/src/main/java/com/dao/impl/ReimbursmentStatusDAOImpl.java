package com.dao.impl;

import com.dao.ReimbursmentStatusDAO;
import com.reimb.ReimbursmentStatus;

public class ReimbursmentStatusDAOImpl implements ReimbursmentStatusDAO {
	
	static{
	       try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	   }
	
	private String url="jdbc:oracle:thin:@wvudatabase.c1euslmkhv46.us-east-2.rds.amazonaws.com:1521:orcl";
	private String usernamee="project1_db";
	private String password="p4ssw0rd";

	@Override
	public ReimbursmentStatus selectReimbStatus(String reimb_status) {
		
		return null;
	}

}
