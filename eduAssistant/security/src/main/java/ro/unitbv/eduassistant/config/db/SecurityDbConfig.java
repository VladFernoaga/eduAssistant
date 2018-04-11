package ro.unitbv.eduassistant.config.db;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "ro.unitbv.eduassistant.security.repo",
    entityManagerFactoryRef = "securityEntityManagerFactory",
    transactionManagerRef = "securityTransactionManager")
public class SecurityDbConfig {

  @Autowired
  private SecurityDbProperties prop;

  @Autowired
  @Qualifier("securityDataSoruce")
  private DataSource datasource;

  @Bean("securityTransactionManager")
  public PlatformTransactionManager transactionManager() {
    EntityManagerFactory factory = entityManagerFactory().getObject();
    return new JpaTransactionManager(factory);
  }

  @Bean("securityEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(Boolean.TRUE);
    vendorAdapter.setShowSql(Boolean.TRUE);

    factory.setDataSource(datasource);
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("ro.unitbv.eduassistant.security.model");

    Map<String, String> jpaProperties = new HashMap<>();
    jpaProperties.put("hibernate.hbm2ddl.auto", prop.getHibernateAuto());
    jpaProperties.put("hibernate.dialect", prop.getHibernateDialect());
    jpaProperties.put("hibernate.show_sql", prop.getHibernateShowSql());
    factory.setJpaPropertyMap(jpaProperties);

    factory.afterPropertiesSet();
    factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
    return factory;
  }

  @Bean("securityHibernateExceptionTranslator")
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

  @Bean("securityDataSoruce")
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(prop.getJdbcDriverClassName());
    dataSource.setUrl(prop.getJdbcUrl());
    dataSource.setUsername(prop.getJdbcUsername());
    dataSource.setPassword(prop.getJdbcPassword());
    return dataSource;
  }
}
