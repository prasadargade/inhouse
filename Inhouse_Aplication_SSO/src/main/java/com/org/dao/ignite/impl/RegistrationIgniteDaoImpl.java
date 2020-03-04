package com.org.dao.ignite.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.org.dao.ignite.IRegistrationIgniteDao;
import com.org.entity.EmployeeEntity;
import com.org.entity.RegistrationEntity;
import com.org.entity.dto.RegistrationDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class RegistrationIgniteDaoImpl extends GenericIgniteDaoImpl<EmployeeEntity, Serializable> implements IRegistrationIgniteDao {

	private static final long serialVersionUID = 1L;

	@Override
	public RegistrationEntity save(RegistrationDto registrationDto) {

		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setFirstName(registrationDto.getFirstName());
		employeeEntity.setLastName(registrationDto.getLastName());
		employeeEntity.setEmail(registrationDto.getEmail());
		
		persist(employeeEntity);
		
		log.info("successfuly rgister employee in ignite");
		return new RegistrationEntity();
	}
}
