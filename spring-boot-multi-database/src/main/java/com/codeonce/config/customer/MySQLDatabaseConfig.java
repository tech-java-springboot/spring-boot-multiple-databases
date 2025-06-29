package com.codeonce.config.customer;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mySqlEntityManagerFactoryBean", transactionManagerRef = "mySqlTransactionManager", basePackages = "com.codeonce.repo.customer")
public class MySQLDatabaseConfig {
	
	//1. Datasource config
	@Bean
	@ConfigurationProperties(prefix = "db2.datasource")
	public DataSource mySqlDatasource() {
		return DataSourceBuilder.create().build();
	}
	
	//2. EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(EntityManagerFactoryBuilder emfb) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		
		return emfb
				.dataSource(mySqlDatasource())
				.packages("com.codeonce.model.customer")
				.properties(properties)
				.persistenceUnit("db2")
				.build();
	}
	
	//3. TransactionManager
	@Bean
	public PlatformTransactionManager mySqlTransactionManager(@Qualifier("mySqlEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	} 
}
