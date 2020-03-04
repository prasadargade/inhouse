package com.org.service.impl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.org.dao.couchbase.IDSEmployeeCouchbaseDao;
import com.org.dao.mysql.IDSEmployeeSQLDao;
import com.org.entity.EmployeeEntity;
import com.org.entity.dto.EmployeeDto;
import com.org.exeception.RegistrationException;
import com.org.service.IEmployeeService;

@Service("DS")
@Validated
public class DSEmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IDSEmployeeSQLDao dsEmployeeSQLService;

	@Autowired
	private IDSEmployeeCouchbaseDao dsEmployeeCouchbaseDao;

	@Override
	@Transactional
	public Long processEmployeeDetails(@Valid EmployeeDto employeeDto) throws RegistrationException {
		dsEmployeeSQLService.save(employeeDto);
		dsEmployeeCouchbaseDao.save(employeeDto);
		return 1L;
	}

	@Override
	public List<EmployeeEntity> processRequestOfGetEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeEntity processRequestOfGetEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeEntity> processRequestOfGetAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeEntity> processGetEmployeeRequest(Map<String, Object> requestParamMap) {
		// TODO Auto-generated method stub
		return null;
	}

}