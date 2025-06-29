package com.codeonce.config.product;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "oracleEntityManagerFactoryBean", transactionManagerRef = "oracleTransactionManager", basePackages = "com.codeonce.repo.product")
public class OracleDatabaseConfig {
	
	//1. Datsource config
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "db1.datasource")
	public DataSource oracleDatasource() {
		return DataSourceBuilder.create().build();
	}
	
	//2. EntityManagerFactory
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactoryBean(EntityManagerFactoryBuilder emfb) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		
		return emfb
				.dataSource(oracleDatasource())
				.packages("com.codeonce.model.product")
				.properties(properties)
				.persistenceUnit("db1")
				.build();
	}
	
	//3. TransactionManager
	@Primary
	@Bean
	public PlatformTransactionManager oracleTransactionManager(@Qualifier("oracleEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
