package com.org.dao.mysql.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.org.dao.mysql.IDSEmployeeSQLDao;
import com.org.dto.EmployeeDto;
import com.org.entity.EmployeeEntity;
import com.org.exeception.RegistrationException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@Transactional
public class DSEmployeeSQLDaoImpl extends GenericJpaDaoImpl<EmployeeEntity, Serializable> implements IDSEmployeeSQLDao {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	public EmployeeEntity save(EmployeeDto employeeDto) throws RegistrationException {

		EmployeeEntity entity = null;
		
		Query query = entityManager.createQuery("FROM EmployeeEntity e WHERE e.email = :email", EmployeeEntity.class);
		query.setParameter("email", employeeDto.getEmail());
		Optional<EmployeeEntity> employee = null;
		try {
			employee = Optional.of((EmployeeEntity) query.getSingleResult());
		} catch (Exception e) {
		}

		if (employee == null) {
			EmployeeEntity employeeEntity = new EmployeeEntity();
			employeeEntity.setFirstName(employeeDto.getFirstName());
			employeeEntity.setLastName(employeeDto.getLastName());
			employeeEntity.setEmail(employeeDto.getEmail());

			entity = persist(employeeEntity);
		}

		if (entity.getId() != null) {
			log.info("Successfully Register Employee in SQL");
			return entity;

		} else {
			log.warn("Unable to Register Employee in SQL");
			throw new RegistrationException("Unable to Register Employee in SQL");
		}
	}

	public EmployeeEntity update(EmployeeDto employeeDto) throws RegistrationException {
		
		Query query = entityManager.createQuery("FROM EmployeeEntity e WHERE e.email = :email", EmployeeEntity.class);
		query.setParameter("email", employeeDto.getEmail());
		Optional<EmployeeEntity> employee = null;
		try {
			employee = Optional.of((EmployeeEntity) query.getSingleResult());
		} catch (Exception e) {
		}

		EmployeeEntity entity = null;
		if (employee != null) {
			EmployeeEntity employeeEntity = employee.get();
			employeeEntity.setEmail(employeeDto.getEmail());
			employeeEntity.setFirstName(employeeDto.getFirstName());
			employeeEntity.setLastName(employeeDto.getLastName());

			entity = persist(employeeEntity);
		}

		if (entity.getId() != null) {
			log.info("Successfully Update Employee in SQL");
			return entity;

		} else {
			log.warn("Unable to Update Employee in SQL");
			throw new RegistrationException("No employee record exist for given id");
		}

	}

	public List<EmployeeDto> getAll() {

		List<EmployeeDto> list = new ArrayList<EmployeeDto>();
		List<EmployeeEntity> result = (List<EmployeeEntity>) entityManager.createQuery("SELECT e FROM EmployeeEntity e").getResultList();
		
		if (result.size() > 0) {
			for (EmployeeEntity employeeEntity : result) {
				EmployeeDto employeeDto = new EmployeeDto();
				employeeDto.setFirstName(employeeEntity.getFirstName());
				employeeDto.setLastName(employeeEntity.getLastName());
				employeeDto.setEmail(employeeEntity.getEmail());

				list.add(employeeDto);
			}
		}
		log.info("Successfully Listed All Employee Records");
		return list;
	}

	public EmployeeDto getById(Long id) throws RegistrationException {

		Optional<EmployeeEntity> employee = Optional.of(entityManager.find(EmployeeEntity.class, id));
		
		if (employee != null) {
			EmployeeEntity employeeEntity = employee.get();

			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setFirstName(employeeEntity.getFirstName());
			employeeDto.setLastName(employeeEntity.getLastName());
			employeeDto.setEmail(employeeEntity.getEmail());

			log.info("Successfully Listed Employee Records");
			return employeeDto;

		} else {
			log.error("No employee record exist for given id");
			throw new RegistrationException("No employee record exist for given id");
		}
	}

	public void deleteById(Long id) throws RegistrationException {
		
		Optional<EmployeeEntity> employee = Optional.of(entityManager.find(EmployeeEntity.class, id));
		if (employee != null) {
			entityManager.remove(id);
			log.error("Successfully Deleted Employee Record");

		} else {
			log.error("No employee record exist for given id");
			throw new RegistrationException("No employee record exist for given id");
		}
	}
	
	
	
	
	
	
	
	
	
	//EMP DAO METHODS
	public <S extends EmployeeEntity> S save(S entity) {
		return entityManager.merge(entity);
	}

	public Optional<EmployeeEntity> findById(Long id) {
		return Optional.of(entityManager.find(EmployeeEntity.class, id));
	}

	public Optional<EmployeeEntity> findByEmail(String email) {
		Query query = entityManager.createQuery("FROM EmployeeEntity e WHERE e.email = :email", EmployeeEntity.class);
		query.setParameter("email", email);

		try {
			return Optional.of((EmployeeEntity) query.getSingleResult());
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Iterable<EmployeeEntity> findAllEMPDAO() {
		return entityManager.createQuery("SELECT e FROM EmployeeEntity e").getResultList();
	}

	public void deleteByIdEMPDAO(Long id) {
		entityManager.remove(id);
	}
}