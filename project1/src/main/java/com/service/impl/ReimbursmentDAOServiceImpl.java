package com.service.impl;

import java.util.List;

import com.dao.ReimbursmentDAO;
import com.dao.impl.ReimbursmentDAOImpl;
import com.reimb.Reimbursment;
import com.service.ReimbursmentDAOService;

public class ReimbursmentDAOServiceImpl implements ReimbursmentDAOService {
	private ReimbursmentDAO reimbdao = new ReimbursmentDAOImpl();
	
	@Override
	public int insertReimbursment(Reimbursment reimb) {
		
		return reimbdao.insertReimb(reimb);
	}

	@Override
	public List<Reimbursment> getAllReimb() {
		return reimbdao.selectAllReimb();
		}

	@Override
	public List<Reimbursment> selectByAuthor(int id) {
		
		return reimbdao.selectByAuthor(id);
	}

	@Override
	public int updateReimb(Reimbursment updateReimb) {
		return reimbdao.updateReimb(updateReimb);
	
	}

	@Override
	public Reimbursment selectById(int reimb_ID) {
		return reimbdao.selectById(reimb_ID);
		
	}

	@Override
	public List<Reimbursment> selectbyStatus(int statusId) {
		return reimbdao.selectReimbursementByStatus(statusId);
		
	}

}
