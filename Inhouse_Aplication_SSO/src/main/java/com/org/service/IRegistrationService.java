package com.org.service;

import javax.validation.Valid;

import com.org.entity.dto.RegistrationDto;

public interface IRegistrationService {

	public Long processRegistrationDetails(@Valid RegistrationDto registrationDto);
	
}
