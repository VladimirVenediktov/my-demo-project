package ru.venediktov.testspringproject.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
@ConditionalOnProperty("spring.cloud.stream.function.style.enabled")
public class RabbitFunctionConfig {

    // если опубликовать сообщение "руками", то залогируем его
    @Bean
    public Consumer<String> log() {
        return str -> log.info("Received: {}", str);
    }
}
