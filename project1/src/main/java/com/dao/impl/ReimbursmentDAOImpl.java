package com.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.ReimbursmentDAO;
import com.reimb.Reimbursment;


public class ReimbursmentDAOImpl implements ReimbursmentDAO {
	
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
	public List<Reimbursment> selectAllReimb() {
		List<Reimbursment> reimb = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, usernamee, password)){
			
			String sql = "SELECT * FROM ers_reimbursement ORDER BY reimb_id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				reimb.add(new Reimbursment(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"),
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimb;
	}
	

	@Override
	public int insertReimb(Reimbursment reimbursment) {
		int changes = 0;
		CallableStatement cs = null;
		try(Connection conn = DriverManager.getConnection(url, usernamee, password)){
			
			String sql = "{call increment_procedure_reimbID(?, ?, ?, ?, ?, ?, ?, ?)";
			cs = conn.prepareCall(sql);
			cs.setDouble(1, reimbursment.getReimb_amount());
			cs.setTimestamp(2, reimbursment.getReimb_submitted());
			cs.setTimestamp(3, reimbursment.getReimb_resolved());
			cs.setString(4, reimbursment.getReimb_description());
			cs.setInt(5, reimbursment.getReimb_author());
			cs.setInt(6, reimbursment.getReimb_resolver());
			cs.setInt(7, reimbursment.getReimb_status_id());
			cs.setInt(8, reimbursment.getReimb_type_id());
			
			
			changes = cs.executeUpdate();
	
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return changes;
	}
	

	@Override
	public Reimbursment selectById(int reimb_ID) {
		try (Connection conn = DriverManager.getConnection(url,usernamee, password)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_id = ?");
			ps.setInt(1, reimb_ID);
			
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Reimbursment(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"),
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
			}
			
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Reimbursment selectByResolver(int reimb_resolver) {
		try (Connection conn = DriverManager.getConnection(url,usernamee, password)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursment WHERE reimb_resolver = ?");
			ps.setInt(1, reimb_resolver);
			
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Reimbursment(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"),
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
			}
			
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		

//	@Override
//	public Reimbursment selectByAuthor(String reimb_author) {
//		try (Connection conn = DriverManager.getConnection(url,usernamee, password)){
//			
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursment WHERE reimb_author = ?");
//			ps.setString(1, reimb_author);
//			
//			
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				return new Reimbursment(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
//						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"),
//						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
//			}
//			
//			return null;
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	
//	}


	@Override
	public List<Reimbursment> selectByAuthor(int id) {
		List<Reimbursment> author = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url,usernamee, password)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_author = ? ORDER BY reimb_id");
			ps.setInt(1, id);
			
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				author.add(new Reimbursment(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), 
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"), rs.getString("reimb_description"),
						rs.getInt("reimb_author"), rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}
			

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return author;
	
}


	@Override
	public int updateReimb(Reimbursment reimbursment) {
		int changes = 0;
		try (Connection conn = DriverManager.getConnection(url, usernamee, password)){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE ERS_REIMBURSEMENT SET reimb_status_id=?, reimb_resolver=?, reimb_resolved=? WHERE reimb_id=?");
			ps.setInt(1, reimbursment.getReimb_status_id());
			ps.setInt(2, reimbursment.getReimb_resolver());
			ps.setTimestamp(3, reimbursment.getReimb_resolved());
			ps.setInt(4, reimbursment.getReimb_ID());

		
			changes= ps.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	
		return changes;
	}
	
	@Override
	public List<Reimbursment> selectReimbursementByStatus(int statusID) {
		List<Reimbursment> status = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, usernamee, password)) {
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statusID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				status.add(new Reimbursment(rs.getInt("REIMB_ID"), rs.getDouble("REIMB_AMOUNT"),
						rs.getTimestamp("REIMB_SUBMITTED"), rs.getTimestamp("REIMB_RESOLVED"), rs.getString("REIMB_DESCRIPTION"),
						rs.getInt("REIMB_AUTHOR"), rs.getInt("REIMB_RESOLVER"),
						rs.getInt("REIMB_STATUS_ID"), rs.getInt("REIMB_TYPE_ID")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}
}
