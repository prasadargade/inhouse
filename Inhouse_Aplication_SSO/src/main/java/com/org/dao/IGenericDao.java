package com.org.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.org.entity.EmployeeEntity;

public interface IGenericDao<T, PK> extends Serializable {

	public List<T> findAll();

	public List<T> findByQuery(String query, Map<String, Object> args);
	
	public List<T> findByQuery(String query, Object[] args);
	
	public List<T> findByNamedQuery(String queryName, Map<String, Object> args);
	
	public List<T> findByNamedQuery(String queryName, Object[] args);
	
	public List<T> findByQuery(String query);
	
	public List<T> findByNamedQuery(String queryName);
	
	public T findById(PK id);
	
	public T persist(T entity);
	
	public void flush();

	public void clear();

	public void remove(T entity);
	
	public Optional<EmployeeEntity> findByEmail(String email);
	
}
