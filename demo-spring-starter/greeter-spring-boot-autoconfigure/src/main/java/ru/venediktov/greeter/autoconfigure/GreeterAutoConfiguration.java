package ru.venediktov.greeter.autoconfigure;

import static ru.venediktov.greeter.GreeterConfigParams.EVENING_MESSAGE;
import static ru.venediktov.greeter.GreeterConfigParams.USER_NAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.venediktov.greeter.Greeter;
import ru.venediktov.greeter.GreetingConfig;

@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {

  @Autowired
  private GreeterProperties greeterProperties;

  @Bean
  @ConditionalOnMissingBean
  public GreetingConfig greeterConfig() {

    String userName = greeterProperties.getUserName();
    String message = greeterProperties.getEveningMessage();
    // ..

    GreetingConfig greetingConfig = new GreetingConfig();
    greetingConfig.put(USER_NAME, userName);
    greetingConfig.put(EVENING_MESSAGE, message);
    // ...
    return greetingConfig;
  }

  @Bean
  @ConditionalOnMissingBean
  public Greeter greeter(GreetingConfig greetingConfig) {
    return new Greeter(greetingConfig);
  }

}
