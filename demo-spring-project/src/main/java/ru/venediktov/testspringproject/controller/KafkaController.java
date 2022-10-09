package ru.venediktov.testspringproject.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.venediktov.testspringproject.services.streams.KafkaProducer;

@ConditionalOnBean(KafkaAdmin.class)
@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
@Slf4j
public class KafkaController {

  private final KafkaProducer kafkaProducer;

  //  curl -X POST -H "Content-type: application/json" -d "message" "http://localhost:8888/api/kafka/send"
  @PostMapping("/send")
  public HttpStatus sendMessage(@RequestBody String message) {
    log.info("Try to send message \"{}\" to kafka topic", message);
    try {
      kafkaProducer.sendMessage(message);
    } catch (Exception e) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      log.error("Message \"{}\" was not sent to kafka topic. Caused by {}", message, sw);
      return HttpStatus.CONFLICT;
    }
    log.info("Message \"{}\" was sent to kafka topic", message);
    return HttpStatus.OK;
  }

}
