package ro.unitbv.eduassistant.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/** The Main Configuration Setup for the DataBase **/
@Configuration
@EnableJpaRepositories(basePackages = "ro.unitbv.eduassistant.repo",
    entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class DbConfig {

  @Autowired
  private Properties prop;

  @Bean
  @Primary
  public PlatformTransactionManager transactionManager() {
    EntityManagerFactory factory = entityManagerFactory().getObject();
    return new JpaTransactionManager(factory);
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(Boolean.TRUE);
    vendorAdapter.setShowSql(Boolean.TRUE);

    factory.setDataSource(dataSource());
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("ro.unitbv.eduassistant.model");

    Map<String, String> jpaProperties = new HashMap<>();
    jpaProperties.put("hibernate.hbm2ddl.auto", prop.getHibernateAuto());
    jpaProperties.put("hibernate.dialect", prop.getHibernateDialect());
    jpaProperties.put("hibernate.show_sql", prop.getHibernateShowSql());
    factory.setJpaPropertyMap(jpaProperties);

    factory.afterPropertiesSet();
    factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    return factory;
  }

  @Bean
  @Primary
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

  @Bean
  @Primary
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(prop.getJdbcDriverClassName());
    dataSource.setUrl(prop.getJdbcUrl());
    dataSource.setUsername(prop.getJdbcUsername());
    dataSource.setPassword(prop.getJdbcPassword());
    return dataSource;
  }

  @Bean
  @Primary
  public JdbcTemplate jdbcTemplate() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
    return jdbcTemplate;
  }
}
