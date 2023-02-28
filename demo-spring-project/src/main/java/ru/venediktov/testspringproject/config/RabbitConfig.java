package ru.venediktov.testspringproject.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@EnableBinding({Source.class, Sink.class})
@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final Source output;

    //  отправка сообщения каждые 10 сек
    @Scheduled(fixedRate = 10000)
    public void publish() {
        LocalDateTime now = LocalDateTime.now();
        output.output().send(MessageBuilder.withPayload(now.toString().getBytes()).build());
    }

    @StreamListener(Sink.INPUT)
    public void listen(String msg) {
        log.info(msg);
    }

}
