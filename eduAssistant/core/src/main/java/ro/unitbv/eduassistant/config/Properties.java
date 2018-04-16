package ro.unitbv.eduassistant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class Properties {  
  /** The hibernate auto. */
  @Value("${hibernate.hbm2ddl.auto}")
  private String hibernateAuto;

  /** The hibernate show sql. */
  @Value("${hibernate.show_sql}")
  private String hibernateShowSql;

  /** The hibernate dialect. */
  @Value("${hibernate.dialect}")
  private String hibernateDialect;

  /** The jdbc driver class name. */
  @Value("${datasource.driverClassName}")
  private String jdbcDriverClassName;

  /** The jdbc url. */
  @Value("${datasource.url}")
  private String jdbcUrl;

  /** The jdbc username. */
  @Value("${datasource.username}")
  private String jdbcUsername;

  /** The jdbc password. */
  @Value("${datasource.password}")
  private String jdbcPassword;


}
