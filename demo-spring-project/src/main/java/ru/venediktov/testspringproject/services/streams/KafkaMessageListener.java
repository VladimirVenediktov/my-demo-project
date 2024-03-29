package ru.venediktov.testspringproject.services.streams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

  @Value(value = "${kafka.groupId}")
  private String groupId;

  @KafkaListener(topics = "first-topic", groupId = "groupId")
  public void listen(String message) {
    log.info("Received Message in group {}: {}", groupId, message);
  }
}
