package com.org.dao.ignite.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.org.dao.ignite.IEmployeeIgniteDao;
import com.org.entity.EmployeeEntity;
import com.org.entity.dto.EmployeeDto;

import lombok.extern.log4j.Log4j;

@Log4j
@Repository
public class EmployeeIgniteDaoImpl extends GenericIgniteDaoImpl<EmployeeEntity, Serializable> implements IEmployeeIgniteDao {
	
	private static final long serialVersionUID = 1L;

	@Override
	public EmployeeEntity save(EmployeeDto employeeDto) {

		EmployeeEntity employeeEntity  = new EmployeeEntity();
		employeeEntity.setFirstName(employeeDto.getFirstName());
		employeeEntity.setLastName(employeeDto.getLastName());
		employeeEntity.setEmail(employeeDto.getEmail());
		
		persist(employeeEntity);
		
		log.info("Successfully reg employee in ignite");
		return new EmployeeEntity();
	}

	@Override
	public List<EmployeeEntity> getEmployeeDetailsByEmail(String email) {
		
		log.debug("inside dao method");
		
		Map<String, Object> map = new HashMap<>();
		map.put("eamil", email);
		List<EmployeeEntity> list = findByQuery("FROM EmployeeEntity e WHERE e.email = :email", map);
		
		System.out.println("FOUND AS :: " + list);;
		return list;
	}

	@Override
	public EmployeeEntity getEmployeeDetailsById(Long id) {
		log.debug("inside dao method");
		
		EmployeeEntity employeeEntity  = findById(id);
		return employeeEntity;
	}

	@Override
	public List<EmployeeEntity> getAllEmployeeDetails() {
		return findAll();
	}

	@Override
	public List<EmployeeEntity> getEmployeeDetails(Map<String, Object> requestParamMap) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
