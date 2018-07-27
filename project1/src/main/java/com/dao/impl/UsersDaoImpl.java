package com.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dao.UsersDAO;
import com.reimb.Users;

public class UsersDaoImpl implements UsersDAO {
	
	static{
	       try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	   }
	
	private String url="jdbc:oracle:thin:@wvudatabase.c1euslmkhv46.us-east-2.rds.amazonaws.com:1521:orcl";
	private String usernamee="project1_db";
	private String passwordd="p4ssw0rd";

	@Override
	public List<Users> selectAllUsers() {
		List<Users> user = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, usernamee, passwordd)){
			
			String sql = "SELECT * FROM ers_users";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				user.add(new Users(rs.getInt("ers_users_id"), rs.getString("ers_username"), 
						rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), rs.getInt("user_role_id")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
		
	@Override
	//NOT USING THIS
	public int insertUser(Users users) {
		try(Connection conn = DriverManager.getConnection(url, usernamee, passwordd)){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ers_users VALUES (?, ?, ?, ?, ?, 0)");
			ps.setString(1, users.getUsername());
			ps.setString(2, users.getPassword());
			ps.setString(3, users.getFirstname());
			ps.setString(4, users.getLastname());
			ps.setString(5, users.getEmail());
			
			
			return ps.executeUpdate();
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public Users selectByUsername(String username) {
		Users u = new Users();
		try (Connection conn = DriverManager.getConnection(url,usernamee, passwordd)){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_users WHERE ers_username = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u= new Users(rs.getInt("ers_users_id"), rs.getString("ers_username"), 
						rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), rs.getInt("user_role_id"));
			}
		}
			
			
		catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public Users selectByFinanceManager() {
		try (Connection conn = DriverManager.getConnection(url,usernamee, passwordd)){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_users WHERE user_role_id=1");

			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Users(rs.getInt("ers_users_id"), rs.getString("ers_username"), 
						rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), rs.getInt("user_role_id"));
			}
			
			return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertUser(String username1, String password1, String firstname, String lastname, String email) {
		int changes =0;
		CallableStatement callst = null;
		try(Connection conn = DriverManager.getConnection(url, usernamee, passwordd)){
			
			
			String sql ="{call increment_procedure_userID(?, ?, ?, ?, ?, 0)}";
			callst = conn.prepareCall(sql);
			callst.setString(1, username1);
			callst.setString(2, password1);
			callst.setString(3, firstname);
			callst.setString(4, lastname);
			callst.setString(5, email);
			
			changes = callst.executeUpdate();
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		return changes;
	}
	
	
}
