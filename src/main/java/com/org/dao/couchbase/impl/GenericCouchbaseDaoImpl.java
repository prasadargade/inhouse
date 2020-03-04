package com.org.dao.couchbase.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

import com.org.dao.IGenericDao;
import com.org.entity.EmployeeEntity;

public class GenericCouchbaseDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CouchbaseTemplate couchbaseTemplate;
	
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByQuery(String query, Map<String, Object> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByQuery(String query, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByNamedQuery(String queryName, Map<String, Object> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByNamedQuery(String queryName, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findByNamedQuery(String queryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(PK id) {
		return null;
	}

	@Override
	public T persist(T entity) {
		couchbaseTemplate.insert(entity);
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<EmployeeEntity> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
