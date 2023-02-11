package ru.venediktov.testspringproject.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private String login;
  private String password;
  private List<String> roles;

  private final CustomUserDetailsService customUserDetailsService;

  // авторизация
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/api/v1/**").fullyAuthenticated()
        .and()
        .csrf().disable()
        .formLogin();
  }

  // аутентификация
  /*@Bean
  @Override
  protected UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager(
        User.builder()
            .username(login)
            .password(passwordEncoder().encode(password))
            .roles(roles.toArray(new String[0]))
            .build());
  }*/

  // другой вариант аутентификации
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService); // используется в DaoAuthenticationProvider (по умолчанию)
    // либо можно реализовать свой AuthenticationProvider и сделать auth.authenticationProvider()
  }

  @Bean
  protected PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

}
