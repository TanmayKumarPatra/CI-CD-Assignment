package com.stackroute.keepnote.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.stackroute.keepnote.aspect.LoggingAspect;

/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @ComponentScan - this annotation is used to search for the Spring components amongst the application
 * @EnableWebMvc - Adding this annotation to an @Configuration class imports the Spring MVC 
 * 				   configuration from WebMvcConfigurationSupport 
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *                  
 * @EnableAspectJAutoProxy - This spring aop annotation is used to enable @AspectJ support with Java @Configuration  
 * */

@Configuration
@ComponentScan(basePackages = "com.stackroute")
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ApplicationContextConfig {

	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. UserName 4. Password
	 */

	/*
	 * Use this configuration while submitting solution in hobbes and CI
	 * dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	 * dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" +
	 * System.getenv("MYSQL_DATABASE")
	 * +"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
	 * dataSource.setUsername(System.getenv("MYSQL_USER"));
	 * dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));
	 */

	/*
	 * create a getter for Hibernate properties here we have to mention 1. show_sql
	 * 2. Dialect 3. hbm2ddl
	 */

	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
	 * class through which we get sessions and perform database operations.
	 */

	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles
	 * transaction in Spring. The application that uses single hibernate session
	 * factory for database transaction has good choice to use
	 * HibernateTransactionManager. HibernateTransactionManager can work with plain
	 * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
	 * ensures data integrity.
	 */
	
	@Bean
	@Autowired
	public DataSource datasource() {
		System.out.println("Inside DataSource");
		BasicDataSource dataSource = new BasicDataSource();
// 		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
// 		dataSource.setUrl("jdbc:mysql://localhost:3306/HibernateDemo1?createDatabaseIfNotExist=true");
// 		dataSource.setUsername("root");
// 		dataSource.setPassword("root");
		
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" +
		System.getenv("MYSQL_DATABASE")
		+"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
		dataSource.setUsername(System.getenv("MYSQL_USER"));
		dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));
		return dataSource;
	}
	@Bean
	@Autowired
	public LocalSessionFactoryBean localsessionfactorybean(DataSource datasource) throws IOException{
		System.out.println("Inside LocalSessionFB");
		LocalSessionFactoryBean localsfb = new LocalSessionFactoryBean();
		localsfb.setDataSource(datasource);
		localsfb.setPackagesToScan("com.stackroute.keepnote");
		localsfb.setHibernateProperties(gethibernateProperties());
		localsfb.afterPropertiesSet();
		return localsfb;
	}
	
	Properties gethibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		prop.put("hibernate.show_sql", "true");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		return prop;
	}
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		System.out.println("Inside HTM");
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	@Bean
	public InternalResourceViewResolver internalresouceviewresolver() {
		InternalResourceViewResolver viewresolver = new InternalResourceViewResolver();
		viewresolver.setPrefix("/WEB-INF/views/");
		viewresolver.setSuffix(".jsp");
		return viewresolver;
	}
	
	@Bean(name="logAspect")
	public LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}

}
