package ru.venediktov.testspringproject.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(
    value="kafka.enabled",
    matchIfMissing = false)
@Configuration
public class KafkaTopicConfig {

  @Bean
  public NewTopic topic1() {
    return new NewTopic("first-topic", 1, (short) 1);
  }

}
