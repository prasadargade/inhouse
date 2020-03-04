package com.org.dao.mysql;

import com.org.dto.RegistrationDto;
import com.org.entity.RegistrationEntity;

public interface IRegistrationMySqlDao {

	RegistrationEntity save(RegistrationDto registrationDto);
	
}
