package com.jtcindia.spring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.hibernate.SessionFactory;
import org.springframework.orm.jpa.vendor.Database;

@SpringBootApplication 
public class JTCAppConfig { 
	@Bean(name="jpaVendorAdapter") 
	public JpaVendorAdapter getJpaVendor() { 
		HibernateJpaVendorAdapter jpaVendor = new HibernateJpaVendorAdapter();
		jpaVendor.setDatabase(Database.MYSQL); //SQL MySQL ke according generate karna.
		jpaVendor.setShowSql(true); //Console me SQL print karo.
		jpaVendor.setGenerateDdl(true); //Table create/update karne ki permission hai.
		jpaVendor.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");

		return jpaVendor;
	}
	
	@Bean(name="jtcEntityFactory")
	public LocalContainerEntityManagerFactoryBean entityFactory(DataSource
	dataSource,JpaVendorAdapter adaptor) {
	LocalContainerEntityManagerFactoryBean factory=new LocalContainerEntityManagerFactoryBean();
	factory.setDataSource(dataSource); //1 Ye DataSource application.properties se aata hai.
	factory.setJpaVendorAdapter(adaptor);//2
	factory.setPackagesToScan("com.jtcindia.spring"); //3
	return factory;
	}

	@Bean(name="jtcJpaTransactionManager")
	public JpaTransactionManager jpaTransactionManager(EntityManagerFactory Factory){
	return new JpaTransactionManager(Factory);
	}
}