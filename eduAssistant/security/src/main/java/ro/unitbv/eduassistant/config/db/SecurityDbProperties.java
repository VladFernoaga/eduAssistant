package ro.unitbv.eduassistant.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;


@Getter


@Setter
@Configuration
/**
 * 
 * The SecurityDbProperties Class
 *
 */
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


}
