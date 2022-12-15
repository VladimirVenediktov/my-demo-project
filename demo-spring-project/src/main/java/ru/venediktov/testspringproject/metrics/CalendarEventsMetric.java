package ru.venediktov.testspringproject.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarEventsMetric {

  private final MeterRegistry registry;

  public void countEvents(int octoberIncrement, int novemberIncrement) {
    Counter octoberCounter = Counter.builder("monthly_stats")
        .tag("month", "november")
        .register(registry);
    Counter novemberCounter = Counter.builder("monthly_stats")
        .tag("month", "october")
        .register(registry);

    IntStream.of(0, octoberIncrement)
        .forEach(octoberCounter::increment);
    IntStream.of(0, novemberIncrement)
        .forEach(novemberCounter::increment);
  }

  @PostConstruct
  public void init() {
    countEvents(1,1);
  }

}