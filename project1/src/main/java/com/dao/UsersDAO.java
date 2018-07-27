package com.dao;

import java.util.List;

import com.reimb.Users;

public interface UsersDAO {
	
	public List<Users> selectAllUsers();
	public Users selectByUsername(String username);
	public int insertUser(Users users);
	public Users selectByFinanceManager();
	public int insertUser(String username, String password, String firstname, String lastname, String email);
}
