package com.org.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.org.dto.EmployeeDto;
import com.org.entity.EmployeeEntity;
import com.org.exeception.RegistrationException;

public interface IEmployeeService {

	public Long processEmployeeDetails(@Valid EmployeeDto employeeDto) throws RegistrationException;
	
	public List<EmployeeEntity> processRequestOfGetEmployeeByEmail(String email);
	
	public EmployeeEntity processRequestOfGetEmployeeById(Long id);
	
	public List<EmployeeEntity> processRequestOfGetAllEmployee();
	
	public List<EmployeeEntity> processGetEmployeeRequest(Map<String, Object> requestParamMap);
	
}
