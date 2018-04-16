package ro.unitbv.eduassistant.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;


//@Getter
//@Setter
@Configuration
public class SecurityDbProperties {
  
  /** The hibernate auto. */
  @Value("${security.hibernate.hbm2ddl.auto}")
  private String hibernateAuto;

  
  /** The hibernate show sql. */
  @Value("${security.hibernate.show_sql}")
  private String hibernateShowSql;

  /** The hibernate dialect. */
  @Value("${security.hibernate.dialect}")
  private String hibernateDialect;

  /** The jdbc driver class name. */
  @Value("${security.datasource.driverClassName}")
  private String jdbcDriverClassName;

  /** The jdbc url. */
  @Value("${security.datasource.url}")
  private String jdbcUrl;

  /** The jdbc username. */
  @Value("${security.datasource.username}")
  private String jdbcUsername;

  /** The jdbc password. */
  @Value("${security.datasource.password}")
  private String jdbcPassword;

public String getHibernateAuto() {
	return hibernateAuto;
}

public void setHibernateAuto(String hibernateAuto) {
	this.hibernateAuto = hibernateAuto;
}

public String getHibernateShowSql() {
	return hibernateShowSql;
}

public void setHibernateShowSql(String hibernateShowSql) {
	this.hibernateShowSql = hibernateShowSql;
}

public String getHibernateDialect() {
	return hibernateDialect;
}

public void setHibernateDialect(String hibernateDialect) {
	this.hibernateDialect = hibernateDialect;
}

public String getJdbcDriverClassName() {
	return jdbcDriverClassName;
}

public void setJdbcDriverClassName(String jdbcDriverClassName) {
	this.jdbcDriverClassName = jdbcDriverClassName;
}

public String getJdbcUrl() {
	return jdbcUrl;
}

public void setJdbcUrl(String jdbcUrl) {
	this.jdbcUrl = jdbcUrl;
}

public String getJdbcUsername() {
	return jdbcUsername;
}

public void setJdbcUsername(String jdbcUsername) {
	this.jdbcUsername = jdbcUsername;
}

public String getJdbcPassword() {
	return jdbcPassword;
}

public void setJdbcPassword(String jdbcPassword) {
	this.jdbcPassword = jdbcPassword;
}

  
  
  

}
