package com.org.dao.mysql.impl;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.org.dao.mysql.IRegistrationMySqlDao;
import com.org.entity.RegistrationEntity;
import com.org.entity.RoleEntity;
import com.org.entity.dto.RegistrationDto;
import com.org.repository.RoleRepository;

public class RegistrationMySqlDaoImpl extends GenericJpaDaoImpl<RegistrationEntity, Serializable> implements IRegistrationMySqlDao {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public RegistrationEntity save(RegistrationDto registrationDto) {
		
		RoleEntity roleEntity = roleRepository.findByName("ROLE_ADMIN");
		
		RegistrationEntity registrationEntity = new RegistrationEntity(); 
		registrationEntity.setFirstName(registrationDto.getFirstName());
		registrationEntity.setLastName(registrationDto.getLastName());
		registrationEntity.setEmail(registrationDto.getEmail());
		registrationEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		registrationEntity.setRoles(Arrays.asList(roleEntity));
		registrationEntity.setEnabled(true);
		
		return registrationEntity;
	}
	
}
