package com.org.dao.couchbase.impl;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.org.dao.couchbase.IRegistrationCouchbaseDao;
import com.org.entity.couchbase.document.RegistrationDocument;
import com.org.entity.dto.RegistrationDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class RegistrationCouchbaseDaoImpl extends GenericCouchbaseDaoImpl<RegistrationDocument, Serializable> implements IRegistrationCouchbaseDao {
	
	private static final long serialVersionUID = 1L;

	@Override
	public RegistrationDocument persistRegistrationEntity(RegistrationDto entity) {

		log.info("started persistRegistrationEntity(RegistrationDto entity)");
		
		RegistrationDto registrationDto = (RegistrationDto) entity;
		
		RegistrationDocument registrationDocument = new RegistrationDocument();
		registrationDocument.setFirstName(registrationDto.getFirstName());
		registrationDocument.setLastName(registrationDto.getLastName());
		registrationDocument.setEmail(registrationDto.getEmail());
		registrationDocument.setPassword(registrationDto.getPassword());
		registrationDocument.setCreated(DateTime.now());
		registrationDocument.setUpdated(DateTime.now());
		
		log.info("ended persistRegistrationEntity(RegistrationDto entity)");
		return registrationDocument;
	}

	
	
}
