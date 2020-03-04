package com.org.dao.ignite;

import com.org.dto.RegistrationDto;
import com.org.entity.RegistrationEntity;

public interface IRegistrationIgniteDao {

	RegistrationEntity save(RegistrationDto entity);
	
}
