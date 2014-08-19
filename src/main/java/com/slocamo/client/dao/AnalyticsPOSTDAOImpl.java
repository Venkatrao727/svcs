package com.slocamo.client.dao;

import org.springframework.transaction.annotation.Transactional;

import com.slocamo.common.BaseDAO;

public class AnalyticsPOSTDAOImpl extends BaseDAO implements AnalyticsPOSTDAO {

	@Transactional
	public void saveToLogTables(Object object) {
		persist(object);
	}

}
