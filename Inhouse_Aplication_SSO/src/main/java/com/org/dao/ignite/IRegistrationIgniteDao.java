package com.org.dao.ignite;

import com.org.entity.RegistrationEntity;
import com.org.entity.dto.RegistrationDto;

public interface IRegistrationIgniteDao {

	RegistrationEntity save(RegistrationDto entity);
	
}
