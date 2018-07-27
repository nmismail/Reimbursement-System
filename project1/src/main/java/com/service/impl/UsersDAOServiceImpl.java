package com.service.impl;

import java.util.List;

import com.dao.UsersDAO;
import com.dao.impl.UsersDaoImpl;
import com.reimb.Users;
import com.service.UsersDAOService;

public class UsersDAOServiceImpl implements UsersDAOService {
	private UsersDAO dao = new UsersDaoImpl();

	//@Override
//	public Users login(String uname) {
//		Users u = dao.selectByUsername(uname);
//		if (u == null) {
//			System.out.println("doesn't exist");
//			return null;
//		}
//		return u;		
//	}

	@Override
	public List<Users> getAllUsers() {
		return dao.selectAllUsers();
	}

	@Override
	public int insertUsers(Users user) {
		return dao.insertUser(user);
		
	}

	@Override
	public Users getFinanceManagers() {
		return dao.selectByFinanceManager();

	}

	@Override
	public int insertUsers(String username, String password, String firstname, String lastname, String email) {
		return dao.insertUser(username, password, firstname, lastname, email);
		
	}

	@Override
	public Users selectByUsername(String username) {
		return dao.selectByUsername(username);
		
	}

	@Override
	public Users login(String uname) {
		// TODO Auto-generated method stub
		return null;
	}

}
