package com.org.config;

import static com.org.constants.InHouseConstants.HIBERNATE_DIALECT;
import static com.org.constants.InHouseConstants.HIBERNATE_HBM2DDL_AUTO;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EntityManagerConfiguration {

	@Value(value = "${mysql.datasource.driver-class-name}")
	private String mysqlDriverClassName;
	
	@Value(value = "${mysql.datasource.url}")
	private String mysqlDatasourceUrl;
	
	@Value(value = "${mysql.datasource.username}")
	private String mysqlDatasourceUsername;
	
	@Value(value = "${mysql.datasource.password}")
	private String mysqlDatasourcePassword;
	
	//Hibernate Configurations
	@Value(value = "${spring.jpa.properties.hibernate.dialect}")
	private String springJpaPopertiesHibernateDialect;
	
	@Value(value = "${spring.jpa.hibernate.ddl-auto}")
	private String springJpaHibernateDdlAuto;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(mysqlDriverClassName);
		dataSource.setUsername(mysqlDatasourceUsername);
		dataSource.setPassword(mysqlDatasourcePassword);
		dataSource.setUrl(mysqlDatasourceUrl);
		return dataSource;
	}
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean  entityManager  = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource());
		entityManager.setPackagesToScan(new String[] {"com.org"});
		
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setJpaProperties(additionalProperties());
		return entityManager;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(HIBERNATE_HBM2DDL_AUTO, springJpaHibernateDdlAuto);
		properties.setProperty(HIBERNATE_DIALECT, springJpaPopertiesHibernateDialect);
		return properties;
	}
}
