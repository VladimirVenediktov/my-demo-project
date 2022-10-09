package ru.venediktov.testspringproject.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

//@Configuration
//@ConfigurationProperties(prefix = "database")
@Getter
@Setter
public class DataBaseConfig {

  private String jdbcUrl;
  private String user;
  private String password;
  private String driverClassName;

  @Bean
  public DataSource dataSource() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(jdbcUrl);
    hikariConfig.setDriverClassName(driverClassName);
    hikariConfig.setAutoCommit(true);
    hikariConfig.setUsername(user);
    hikariConfig.setPassword(password);

    return new HikariDataSource(hikariConfig);
  }

}
