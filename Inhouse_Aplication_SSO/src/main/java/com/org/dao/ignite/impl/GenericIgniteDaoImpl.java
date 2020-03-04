package com.org.dao.ignite.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.org.dao.IGenericDao;
import com.org.entity.EmployeeEntity;
import java.util.Calendar;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Transactional("ignite")
public class GenericIgniteDaoImpl<T, PK extends Serializable> implements IGenericDao<T, PK> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EntityManager entityManager;
	
	protected Class<T> classType;
	
	@SuppressWarnings("unchecked")
	public GenericIgniteDaoImpl() {
		this.classType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getClassType() {
		return classType;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
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
		T t = getEntityManager().find(getClassType(), id);
		return t;
	}

	@Override
	public T persist(T entity) {
		getEntityManager().persist(entity);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getEntityManager().createQuery("SELECT o FROM " + getClassType().getSimpleName() + " o").getResultList();
	}
	
	@Override
	public Optional<EmployeeEntity> findByEmail(String email) {
		log.debug("inside GenericIgniteDaoImpl");
		
		Query query = getEntityManager().createQuery("FROM EmployeeEntity e WHERE e.email = :email", EmployeeEntity.class);
		query.setParameter("email", email);
		
		log.debug("Query :: " +query);
		try {
			log.debug("Query exec started");
			Optional<EmployeeEntity> t = Optional.of((EmployeeEntity) query.getSingleResult());
			log.debug("Query executed");
			return t;
		} catch(Exception e) {
			log.debug("Exceptoin occured");
			return null;
		}
	}
	
	@Override
	public List<T> findByQuery(String query, Map<String, Object> args) {
		return findByQuery(getEntityManager().createQuery(query), args);
	}
	
	protected List<T> findByQuery(Query q, Map<String, Object> args) {

		if(args != null) {
			for (Map.Entry<String, Object> entry : args.entrySet()) {
				if(entry.getValue() instanceof Date) {
					q.setParameter(entry.getKey(), (Date) entry.getValue(), TemporalType.TIMESTAMP);
				} else if (entry.getValue() instanceof Calendar) {
					q.setParameter(entry.getKey(), (Calendar) entry.getValue(), TemporalType.TIMESTAMP);
				} else {
					q.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}

		@SuppressWarnings("unchecked")
		List<T> results = q.getResultList();
		return results;
	}
	
	@Override
	public void flush() {
	}

	@Override
	public void clear() {
	}

	@Override
	public void remove(T entity) {
	}

}
