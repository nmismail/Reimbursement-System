package com.service;

import com.reimb.ReimbursmentType;

public interface ReimbursmentTypeDAOService {

	public ReimbursmentType getById(int reimbtypeid);
	public ReimbursmentType getByType(String reimbtype);
}
