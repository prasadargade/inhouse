package com.org.service;

import javax.validation.Valid;
import com.org.dto.RegistrationDto;

public interface IRegistrationService {

	public Long processRegistrationDetails(@Valid RegistrationDto registrationDto);
	
}
