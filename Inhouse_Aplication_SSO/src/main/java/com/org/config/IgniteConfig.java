package com.org.config;

import org.apache.ignite.binary.BinaryBasicNameMapper;
import org.apache.ignite.configuration.BinaryConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.TransactionConfiguration;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;
import org.hibernate.ogm.datastore.ignite.IgniteConfigurationBuilder;

public class IgniteConfig implements IgniteConfigurationBuilder {
	
	private static IgniteConfiguration config = null;

	@Override
	public IgniteConfiguration build() {

		if(config != null) {
			return config;
		}
		
		IgniteConfiguration config = getInstance();
		config.setIgniteInstanceName("hibernate-grid");
		
		BinaryConfiguration binaryConfiguration = new BinaryConfiguration();
		binaryConfiguration.setNameMapper(new BinaryBasicNameMapper(true));
		binaryConfiguration.setCompactFooter(false);
		config.setBinaryConfiguration(binaryConfiguration);
		
		TransactionConfiguration transactsionConfiguration = new TransactionConfiguration();
		transactsionConfiguration.setDefaultTxConcurrency(TransactionConcurrency.PESSIMISTIC);
		transactsionConfiguration.setDefaultTxIsolation(TransactionIsolation.READ_COMMITTED);
		config.setTransactionConfiguration(transactsionConfiguration);
		return config;
	}

	public static IgniteConfiguration getInstance() {
		if(config == null)
			config = new IgniteConfiguration();
		
		return config;
	}
	
}
