package com.org.dao.couchbase;

import com.org.entity.couchbase.document.RegistrationDocument;
import com.org.entity.dto.RegistrationDto;

public interface IRegistrationCouchbaseDao {

	RegistrationDocument persistRegistrationEntity(RegistrationDto entity);
	
}
