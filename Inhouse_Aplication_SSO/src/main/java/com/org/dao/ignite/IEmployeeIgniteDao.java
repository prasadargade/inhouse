package com.org.dao.ignite;

import java.util.List;
import java.util.Map;

import com.org.entity.EmployeeEntity;
import com.org.entity.dto.EmployeeDto;

public interface IEmployeeIgniteDao {

	EmployeeEntity save(EmployeeDto employeeDto);
	
	List<EmployeeEntity> getEmployeeDetailsByEmail(String email);
	
	EmployeeEntity getEmployeeDetailsById(Long id);
	
	List<EmployeeEntity> getAllEmployeeDetails();
	
	List<EmployeeEntity> getEmployeeDetails(Map<String, Object> requestParamMap);
	
	
}
