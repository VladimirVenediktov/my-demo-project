package ru.venediktov.testspringproject.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.handler.MappedInterceptor;
import ru.venediktov.testspringproject.metrics.RequestMetricInterceptor;

@Configuration
public class CommonConfig {

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }

  // нужен для решения проблемы пропадания некоторых метрик, отдаваемых в Prometheus, после включения в проект
  // spring-boot-starter-data-jpa
  @Bean
  InitializingBean forcePrometheusPostProcessor(BeanPostProcessor meterRegistryPostProcessor,
      PrometheusMeterRegistry registry) {
    return () -> meterRegistryPostProcessor.postProcessAfterInitialization(registry, "");
  }

  @Bean
  public MappedInterceptor metricInterceptor(MeterRegistry registry) {
    return new MappedInterceptor(new String[]{"/**"},
        new RequestMetricInterceptor(registry));
  }

}
