package com.org.dao.couchbase;

import com.org.document.RegistrationDocument;
import com.org.dto.RegistrationDto;

public interface IRegistrationCouchbaseDao {

	RegistrationDocument persistRegistrationEntity(RegistrationDto entity);
	
}
