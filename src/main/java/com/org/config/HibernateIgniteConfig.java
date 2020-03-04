package com.org.config;

import static org.hibernate.ogm.cfg.OgmProperties.DATASTORE_PROVIDER;
import static org.hibernate.ogm.cfg.OgmProperties.ENABLED;
import static org.hibernate.ogm.datastore.ignite.IgniteProperties.CONFIGURATION_CLASS_NAME;
import static org.hibernate.ogm.datastore.ignite.IgniteProperties.IGNITE_INSTANCE_NAME;

import javax.persistence.EntityManager;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.datastore.ignite.impl.IgniteDatastoreProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple;
import com.org.entity.EmployeeEntity;

@Configuration
@EnableTransactionManagement
public class HibernateIgniteConfig {

	@Value(value = "${ignite.instance.name}")
	private String igniteInstanceName;
	
	@Bean
	public EntityManager entityManager() {
		
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySetting(ENABLED, true);
		builder.applySetting(DATASTORE_PROVIDER, IgniteDatastoreProvider.class.getName());
		builder.applySetting(CONFIGURATION_CLASS_NAME, IgniteConfig.class.getName());
		builder.applySetting(IGNITE_INSTANCE_NAME, igniteInstanceName);
		
		return new MetadataSources(builder.build())
				.addAnnotatedClass(EmployeeEntity.class)
				.buildMetadata()
				.getSessionFactoryBuilder()
				.unwrap(OgmSessionFactoryBuilder.class)
				.build()
				.createEntityManager();
				
	}	
	
	@Bean("ignite")
	public PlatformTransactionManager jtaTransactionManager() {
		JtaTransactionManager tm = new JtaTransactionManager();
		tm.setTransactionManager(transactionManager());
		return tm;
	}
	
	public TransactionManagerImple transactionManager() {
		return new TransactionManagerImple();
	}
}
