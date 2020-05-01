package com.kk.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@PropertySource("classpath:/appProperties.properties")
public class AppConfig {

	private String url = "jdbc:mysql://localhost:3306/kushdb";

	private String userName = "root";

	private String password = "password";

	private String driver = "java.sql.Driver";

	@Bean(name = "viewResolverBean")
	public InternalResourceViewResolver irvr() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/");
		irvr.setSuffix(".jsp");
		return irvr;
	}

	@Bean(name = "dataSourceBean")
	public DriverManagerDataSource getDataSourceBean() {

		DriverManagerDataSource dsBean = new DriverManagerDataSource();
		dsBean.setDriverClassName(driver);
		dsBean.setUsername(userName);
		dsBean.setPassword(password);
		dsBean.setUrl(url);

		return dsBean;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerBean() {
		LocalContainerEntityManagerFactoryBean entityManagerBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerBean.setPersistenceUnitName("myPersistence");
		entityManagerBean.setDataSource(getDataSourceBean());

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);

		entityManagerBean.setJpaVendorAdapter(vendorAdapter);

		Map<String, String> jpaPropertyMap = new HashMap<>();
		jpaPropertyMap.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		jpaPropertyMap.put("hibernate.hbm2ddl.auto", "none");
		jpaPropertyMap.put("hibernate.format_sql", "true");

		entityManagerBean.setJpaPropertyMap(jpaPropertyMap);

		return entityManagerBean;

	}

}
