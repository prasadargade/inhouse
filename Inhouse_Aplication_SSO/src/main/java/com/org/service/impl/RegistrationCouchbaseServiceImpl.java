package com.org.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.org.entity.couchbase.document.RegistrationDocument;
import com.org.entity.dto.RegistrationDto;
import com.org.service.IRegistrationCouchbaseService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RegistrationCouchbaseServiceImpl implements IRegistrationCouchbaseService {

	@Autowired
	private CouchbaseTemplate couchbaseTemplate;

	@Override
	public RegistrationDto findOne(String id) {
		return null;
	}

	@Override
	public List<RegistrationDto> findAll() {
		return null;
	}

	@Override
	public List<RegistrationDto> findByFirstName(String firstName) {
		return null;
	}

	@Override
	public void create(RegistrationDto registrationDto) {
		// TODO Auto-generated method stub
		RegistrationDocument registrationDocument = new RegistrationDocument();
		registrationDocument.setFirstName(registrationDto.getFirstName());
		registrationDocument.setLastName(registrationDto.getLastName());
		registrationDocument.setEmail(registrationDto.getEmail());
		registrationDocument.setPassword(registrationDto.getPassword());
		registrationDocument.setCreated(DateTime.now());
		registrationDocument.setUpdated(DateTime.now());

		couchbaseTemplate.insert(registrationDocument);
		
		log.info("Successfull Register User in CB");
	}

	@Override
	public void update(RegistrationDto registrationDto) {

	}

	@Override
	public void delete(RegistrationDto registrationDto) {
	}
}
