package com.dao;

import com.reimb.ReimbursmentType;

public interface ReimbursmentTypeDAO {

	public ReimbursmentType selectReimbByTypeId(int reimb_type_id);
	public ReimbursmentType selectReimbByType(String reimb_type);
}
