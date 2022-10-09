package ru.venediktov.testspringproject.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class RequestMetricInterceptor implements HandlerInterceptor {

  private static final String CUSTOM_REQUEST = "custom_request";
  private static final String URI = "uri";

  private final MeterRegistry registry;

  /**
   * Сбор метрик, показывающих какое кол-во раз вызывался определенный HTTP запрос.
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
      @Nullable Exception ex) throws Exception {
    Optional.ofNullable(request.getRequestURI())
        .ifPresent(uri -> {
          Counter counter = Counter.builder(CUSTOM_REQUEST).tag(URI, uri).register(registry);
          counter.increment();
        });
  }

}
