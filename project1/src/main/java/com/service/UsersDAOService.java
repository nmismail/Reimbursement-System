package com.service;

import java.util.List;

import com.reimb.Users;

public interface UsersDAOService {

	public Users login (String uname);
	
	public List<Users> getAllUsers();
	
	//NOT USING THIS
	public int insertUsers(Users user);
	
	public Users getFinanceManagers();

	public int insertUsers(String username, String password, String firstname, String lastname, String email);
	
	public Users selectByUsername(String username);
	
}
