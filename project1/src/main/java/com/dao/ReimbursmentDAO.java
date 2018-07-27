package com.dao;

import java.util.List;

import com.reimb.Reimbursment;

public interface ReimbursmentDAO {

	public List<Reimbursment> selectAllReimb();
	public int insertReimb(Reimbursment reimbursment);
	public Reimbursment selectById(int reimb_ID);
	public Reimbursment selectByResolver(int reimb_resolver);
	public List <Reimbursment>selectByAuthor(int id);
	public int updateReimb(Reimbursment reimbursment);
	List<Reimbursment> selectReimbursementByStatus(int statusID);
	
	
	//public int insertNewReimb(double reimb_amount, String reimb_submitted, String reimb_resolved, String reimb_description,);
}
