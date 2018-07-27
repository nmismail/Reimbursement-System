package com.service.impl;

import com.dao.ReimbursmentTypeDAO;
import com.dao.impl.ReimbursmentTypeDAOImpl;
import com.reimb.ReimbursmentType;
import com.service.ReimbursmentTypeDAOService;

public class ReimbursmentTypeDAOServiceImpl implements ReimbursmentTypeDAOService {
	private ReimbursmentTypeDAO reimbTypeDao = new ReimbursmentTypeDAOImpl();

	@Override
	public ReimbursmentType getById(int reimbtypeid) {
		return reimbTypeDao.selectReimbByTypeId(reimbtypeid);
		
	}

	@Override
	public ReimbursmentType getByType(String reimbtype) {
		return reimbTypeDao.selectReimbByType(reimbtype);
		
	}

}
