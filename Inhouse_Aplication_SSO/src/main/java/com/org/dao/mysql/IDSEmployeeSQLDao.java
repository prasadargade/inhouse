package com.org.dao.mysql;

import java.util.List;

import com.org.entity.EmployeeEntity;
import com.org.entity.dto.EmployeeDto;
import com.org.exeception.RegistrationException;

public interface IDSEmployeeSQLDao {

	public EmployeeEntity save(EmployeeDto employeeDto) throws RegistrationException;

	public EmployeeEntity update(EmployeeDto employeeDto) throws RegistrationException;

	public List<EmployeeDto> getAll();

	public EmployeeDto getById(Long id) throws RegistrationException;

	public void deleteById(Long id) throws RegistrationException;
}
