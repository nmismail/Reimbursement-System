package com.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.ReimbursmentTypeDAO;
import com.reimb.ReimbursmentType;

public class ReimbursmentTypeDAOImpl implements ReimbursmentTypeDAO {
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
	public ReimbursmentType selectReimbByTypeId(int reimb_type_id) {
		ReimbursmentType typeId = new ReimbursmentType();
		try (Connection conn = DriverManager.getConnection(url,usernamee, password)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement_type WHERE reimb_type_id = ?");
			ps.setInt(1, reimb_type_id);
			
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				typeId= new ReimbursmentType(rs.getInt("reimb_type_id"), rs.getString("reimb_type"));
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return typeId;
	}
	

	@Override
	public ReimbursmentType selectReimbByType(String reimb_type) {
		
		ReimbursmentType type = new ReimbursmentType();
		try (Connection conn = DriverManager.getConnection(url,usernamee, password)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement_type WHERE reimb_type = ?");
			ps.setString(1, reimb_type);
			
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				type= new ReimbursmentType(rs.getInt("reimb_type_id"), rs.getString("reimb_type"));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return type;
	
	}


	



	
	

}
