package com.slocamo.common;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

/**
 * @author vvr@slocamo.com
 *
 */
public class BaseDAO {
	@PersistenceContext
	private  EntityManager entityManager;

	public  List<?> executeToList(String queryString, Integer limit,
			Integer offset, Map<String, Object> params)
			throws IllegalStateException, IllegalArgumentException,
			RollbackException {
		Query query = entityManager.createNamedQuery(queryString);
		if (offset != null) {
			query.setFirstResult(offset);
		}

		if (limit != null) {
			query.setMaxResults(limit);
		}
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		@SuppressWarnings("unchecked")
		List<Object> list = query.getResultList();
		return list;
	}

	public  Object find(Class<?> cls, Object id)
			throws IllegalStateException, IllegalArgumentException,
			RollbackException {
		return entityManager.find(cls, id);
		
	}

	public void persist(Object obj) throws IllegalStateException,
			EntityExistsException, IllegalArgumentException,
			TransactionRequiredException {
		entityManager.persist(obj);
	}
}
