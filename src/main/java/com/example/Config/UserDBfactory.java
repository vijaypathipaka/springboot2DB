package com.example.Config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
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

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "userentityManagerFactoryBean", basePackages = {
		"com.example.Repo.user" }, transactionManagerRef = "usertransactionManager")
@EnableTransactionManagement
public class UserDBfactory {

	@Primary
	@Bean(name = "userDataSource")
	@ConfigurationProperties(prefix = "spring.user.datasource")
	public DataSource dataSource() {

		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "userentityManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("userDataSource") DataSource dataSource) {

		HashMap<String, String> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.example.entity.user")
				.persistenceUnit("User").build();

	}

	@Primary
	@Bean(name = "usertransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("userentityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;

		// return new JpaTransactionManager(entityManagerFactory);

	}
}
