package com.service;

import java.util.List;

import com.reimb.Reimbursment;

public interface ReimbursmentDAOService {

	public int insertReimbursment(Reimbursment reimb);
	
	public List<Reimbursment> getAllReimb();
	
	public List<Reimbursment> selectByAuthor(int id);
	
	public int updateReimb(Reimbursment updateReimb);
	
	public Reimbursment selectById(int reimb_ID);
	
	public List<Reimbursment> selectbyStatus(int statusId);
}
