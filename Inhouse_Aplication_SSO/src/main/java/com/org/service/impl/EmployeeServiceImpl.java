package com.org.service.impl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.org.dao.ignite.IEmployeeIgniteDao;
import com.org.entity.EmployeeEntity;
import com.org.entity.dto.EmployeeDto;
import com.org.service.IEmployeeService;
import com.org.validation.ValidAuthorize;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("NONDS")
@Validated
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeIgniteDao iEmployeeIgniteDao;

	@ValidAuthorize
	public Long processEmployeeDetails(@Valid EmployeeDto employeeDto) {
		iEmployeeIgniteDao.save(employeeDto);
		return 1L;
	}

	@ValidAuthorize
	public List<EmployeeEntity> processRequestOfGetEmployeeByEmail(String email) {
		log.debug("inside service method");

		List<EmployeeEntity> employeeEntities = iEmployeeIgniteDao.getEmployeeDetailsByEmail(email);

		log.debug("end method");
		return employeeEntities;
	}

	@ValidAuthorize
	public EmployeeEntity processRequestOfGetEmployeeById(Long id) {
		log.debug("inside service method");

		EmployeeEntity employeeEntity = iEmployeeIgniteDao.getEmployeeDetailsById(id);

		log.debug("end method");

		return employeeEntity;
	}

	@ValidAuthorize
	public List<EmployeeEntity> processRequestOfGetAllEmployee() {
		return iEmployeeIgniteDao.getAllEmployeeDetails();
	}

	@Override
	public List<EmployeeEntity> processGetEmployeeRequest(Map<String, Object> requestParamMap) {
		List<EmployeeEntity> employeeEntityList = iEmployeeIgniteDao.getEmployeeDetails(requestParamMap);
		return employeeEntityList ;
	}

}
