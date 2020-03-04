package com.org.dao.mysql;

import com.org.entity.RegistrationEntity;
import com.org.entity.dto.RegistrationDto;

public interface IRegistrationMySqlDao {

	RegistrationEntity save(RegistrationDto registrationDto);
	
}
