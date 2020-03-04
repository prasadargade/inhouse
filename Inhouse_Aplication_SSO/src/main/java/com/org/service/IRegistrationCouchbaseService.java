package com.org.service;

import java.util.List;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;

import com.org.entity.dto.RegistrationDto;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "RegistrationDocument", viewName = "RegistrationAll")
public interface IRegistrationCouchbaseService {

	RegistrationDto findOne(String id);

	List<RegistrationDto> findAll();

	List<RegistrationDto> findByFirstName(String firstName);

	void create(RegistrationDto registrationDto);

	void update(RegistrationDto registrationDto);

	void delete(RegistrationDto registrationDto);
}
